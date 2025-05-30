package com.example.demo.app.api.board.usecase.handler

import com.example.demo.app.api.board.usecase.handler.dto.BoardDto
import com.example.demo.core.board.domain.Board
import com.example.demo.core.board.port.BoardWriter
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

// Assumptions for Board and BoardDto:
// data class Board(val id: Long? = null, val title: String, val content: String, val tags: MutableList<String>)
// data class BoardDto(val id: Long, val title: String, val content: String, val tags: List<String>) {
//     companion object {
//         // This fromDomain method will throw an NPE if board.id is null and id is non-nullable in BoardDto
//         fun fromDomain(board: Board): BoardDto =
//             BoardDto(id = board.id!!, title = board.title, content = board.content, tags = board.tags)
//     }
// }
// The BoardsCreateHandler under test is assumed to be:
// class BoardsCreateHandler(private val boardWriter: BoardWriter) {
//     fun handle(args: Args): BoardDto = Board(args.title, args.content, args.tags as MutableList<String>)
//         .run { // `this` is the newly created Board instance (boardInHandler)
//             boardWriter.write(this) // writer returns a Board with an ID, but this return value is ignored by the SUT
//             BoardDto.fromDomain(this) // `fromDomain` is called on boardInHandler, whose ID might still be null
//         }
//     data class Args(val title: String, val content: String, val tags: List<String>)
// }

class BoardsCreateHandlerTest : BehaviorSpec({

    lateinit var boardWriter: BoardWriter
    lateinit var boardsCreateHandler: BoardsCreateHandler

    beforeEach {
        boardWriter = mockk<BoardWriter>()
        boardsCreateHandler = BoardsCreateHandler(boardWriter)
    }

    afterEach {
        unmockkAll()
    }

    Given("Args with title, content, and tags for a new board") {
        val testTitleFromArgs = "Test Title From Args"
        val testContentFromArgs = "Test Content From Args"
        val testTagsFromArgs = listOf("tagArg1", "tagArg2")
        val inputArgs = BoardsCreateHandler.Args(
            title = testTitleFromArgs,
            content = testContentFromArgs,
            tags = testTagsFromArgs
        )

        val expectedBoardId = 1L
        // This is the Board instance that the mocked `boardWriter.write` will return.
        val boardReturnedByWriter = Board(
            id = expectedBoardId,
            title = testTitleFromArgs,      // Assuming writer returns the same title/content
            content = testContentFromArgs,  // for simplicity in this example.
            tags = testTagsFromArgs.toMutableList()
        )

        // Mock `boardWriter.write` to return `boardReturnedByWriter` when any Board object is passed.
        every { boardWriter.write(any()) } returns boardReturnedByWriter

        When("the handle method is called with these Args") {
            // Note: If BoardDto.fromDomain expects a non-null ID, and the SUT doesn't use
            // the boardReturnedByWriter, this line might throw an exception (e.g., NPE).
            // This is part of what the test would uncover.
            val resultDto = boardsCreateHandler.handle(inputArgs)

            Then("BoardWriter.write should be called exactly once") {
                // Verify that the write method was called on the writer.
                // We are not inspecting the argument passed to `write` in detail here,
                // as the focus is on the state of the `resultDto`.
                verify(exactly = 1) { boardWriter.write(any()) }
            }

            And("the BoardDto returned by handle should reflect data from input Args and the ID from the mocked writer's response") {
                // State-based verification:
                // The DTO's `id` should match the `id` from `boardReturnedByWriter`.
                // The DTO's `title`, `content`, and `tags` should match those from `inputArgs`.

                // IMPORTANT: This assertion for `id` is expected to FAIL with the current SUT,
                // because the SUT calls `BoardDto.fromDomain(this)` on the original board instance,
                // not on the `boardReturnedByWriter`. This highlights that the SUT doesn't use
                // the (potentially ID-populated) Board object returned by the writer.
                resultDto.id shouldBe expectedBoardId
                resultDto.title shouldBe testTitleFromArgs
                resultDto.content shouldBe testContentFromArgs
                resultDto.tags shouldBe testTagsFromArgs
            }
        }
    }
})
