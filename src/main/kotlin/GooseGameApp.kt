class GooseGameApp(private val outputStream: OutputPrinter, private val playerRepository: PlayerRepository) {

    fun exec(command: String) {
        val str = CommandFactory().build(command, playerRepository).exec()
        outputStream.printLine(str)
    }

}

