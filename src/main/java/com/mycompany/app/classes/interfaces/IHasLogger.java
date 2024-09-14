package com.mycompany.app.classes.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface IHasLogger {
    final Logger CONSOLE_ERROR = LogManager.getLogger("ConsoleErrorLogger");
    final Logger CONSOLE = LogManager.getLogger("ConsoleLogger");
}
