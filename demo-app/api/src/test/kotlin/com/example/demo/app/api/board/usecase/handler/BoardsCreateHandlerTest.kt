package com.example.demo.app.api.board.usecase.handler

import com.example.demo.app.api.board.usecase.handler.dto.BoardDto
import com.example.demo.core.board.domain.Board
import com.example.demo.core.board.port.BoardWriter
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

// Assumptions for this test to align with the SUT's current structure:
// 1. `com.example.demo.core.board.domain.Board` is a data class (or similar) that includes:
//    - `var id: Long? = null` (or some way to set the ID after construction)
//    - `val title: String`
//    - `val content: String`
//    - `val tags: MutableList<String>`
//    Example: data class Board(var id: Long? = null, val title: String, val content: String, val tags: MutableList<String>)
//
// 2. `com.example.demo.app.api.board.usecase.handler.dto.BoardDto` has a companion object method:
//    `fun fromDomain(board: Board): BoardDto`
//    And this method expects `board.id` to be non-null.
//    Example: data class BoardDto(val id: Long, val title: String, val content: String, val tags: List<String>) {
//                 companion object {
//                     fun fromDomain(board: Board): BoardDto =
//                         BoardDto(id = board.id!!, title = board.title, content = board.content, tags = board.tags)
//                 }
//             }
//
// 3. `com.example.demo.core.board.port.BoardWriter` has a method:
//    `fun write(board: Board)` which may return `Unit` or `Board`.
//    Crucially, this `write` method is assumed to set the `id` on the `Board` instance passed to it.

class BoardsCreateHandlerTest : BehaviorSpec({

    lateinit var boardWriter: BoardWriter
    lateinit var boardsCreateHandler: BoardsCreateHandler

    beforeEach {
        // Initialize MockK for each test if not using @MockK annotation processing
        // MockKAnnotations.init(this, relaxUnitFun = true) // if needed, but direct mockk{} is fine
        boardWriter = mockk<BoardWriter>()
        boardsCreateHandler = BoardsCreateHandler(boardWriter)
    }

    afterEach {
        unmockkAll()
    }

    Given("Args with title, content, and tags for a new board") {
        val testTitle = "Test Title"
        val testContent = "Test Content"
        val testTags = listOf("tag1", "tag2")
        val inputArgs = BoardsCreateHandler.Args(
            title = testTitle,
            content = testContent,
            tags = testTags
        )

        val boardSlot = slot<Board>()
        val expectedBoardId = 1L

        // Mock `boardWriter.write` to capture the Board argument and simulate ID assignment.
        // This relies on the assumption that `Board.id` is mutable or `Board` provides a setter for `id`.
        every { boardWriter.write(capture(boardSlot)) } answers {
            // Simulate that the `write` operation assigns an ID to the `Board` instance.
            // This requires `boardSlot.captured.id` to be assignable.
            // If `Board` is defined as: `data class Board(var id: Long? = null, ...)`
            // then the following line would work if run within this `answers` lambda:
            // `boardSlot.captured.id = expectedBoardId`
            // For the test, we assume this side-effect happens.
            // If `BoardWriter.write` returns Unit, this lambda should also return Unit.
            // If it returns the Board, it should return `boardSlot.captured`.
            // Let's assume it returns Unit as SUT doesn't use the return value.
            val capturedBoard = boardSlot.captured
            // Manually set the ID on the captured board to simulate persistence layer behavior.
            // This requires `Board` to have a mutable `id` or a setter method.
            // This is a strong assumption made for this test.
            val fieldId = Board::class.java.getDeclaredField("id")
            fieldId.isAccessible = true
            fieldId.set(capturedBoard, expectedBoardId)
            // Unit // Explicitly return Unit if that's what `write` signature returns
        }

        When("the handle method is called with these Args") {
            val resultDto = boardsCreateHandler.handle(inputArgs)

            Then("BoardWriter.write should be called exactly once with the correct Board object") {
                verify(exactly = 1) { boardWriter.write(boardSlot.captured) }

                val capturedBoardInstance = boardSlot.captured
                capturedBoardInstance.title shouldBe testTitle
                capturedBoardInstance.content shouldBe testContent
                capturedBoardInstance.tags shouldBe testTags

                // Verify that the ID was indeed set on the captured Board instance by the mock.
                // This confirms the mock's behavior which is crucial for the SUT to then create a DTO with an ID.
                val fieldId = Board::class.java.getDeclaredField("id")
                fieldId.isAccessible = true
                val actualId = fieldId.get(capturedBoardInstance)
                actualId shouldBe expectedBoardId
            }

            And("the BoardDto returned by handle should have the correct id, title, content, and tags") {
                resultDto.id shouldBe expectedBoardId
                resultDto.title shouldBe testTitle
                resultDto.content shouldBe testContent
                resultDto.tags shouldBe testTags
            }
        }
    }
})
