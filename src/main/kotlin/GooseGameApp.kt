class GooseGameApp(private val outputStream: OutputPrinter, private val commandFactory: CommandFactory) {

    fun exec(command: String) {
        val str = commandFactory.build(command).exec()
        outputStream.printLine(str)
    }

}

