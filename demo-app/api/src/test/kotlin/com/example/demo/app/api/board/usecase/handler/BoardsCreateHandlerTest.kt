package com.example.demo.app.api.board.usecase.handler

import com.example.demo.core.board.port.BoardWriter
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.*
import io.mockk.*

class BoardsCreateHandlerTest : BehaviorSpec(
    {
        var boardWriter = mockk<BoardWriter>()
        var boardsCreateHandler = BoardsCreateHandler(boardWriter)
//        every { boardWriter.write(any()) } just runs
        every { boardWriter.write(any()) } returns Unit

        afterEach { unmockkAll() }

        Given("Args with title, content, and tags for a new board") {
            val title = "Test Title From Args"
            val content = "Test Content From Args"
            val tags = listOf("tagArg1", "tagArg2")
            val handlerArgs = BoardsCreateHandler.Args(title, content, tags)

            When("the handle method is called with these Args") {
                val resultDto = boardsCreateHandler.handle(handlerArgs)

                Then("BoardWriter.write should be called exactly once") {
                    verify(exactly = 1) { boardWriter.write(any()) }
                    resultDto.title shouldBe title
                    resultDto.content shouldBe content
                    resultDto.tags should be(tags)
                }
            }
        }
    },
)
