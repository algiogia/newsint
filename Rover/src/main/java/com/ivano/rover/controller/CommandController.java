package com.ivano.rover.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ivano.rover.exception.RoverException;
import com.ivano.rover.model.Command;
import com.ivano.rover.service.DefaultConsole;

@Controller
public class CommandController {

	private static final Logger logger = LoggerFactory
			.getLogger(CommandController.class);

	private DefaultConsole console = new DefaultConsole();

	@RequestMapping(value = "/")
	public ModelAndView sendCommand(@ModelAttribute Command command) {

		ModelAndView model = new ModelAndView();
		model.setViewName("console");
		model.addObject("command", new Command());
		model.addObject("comm", command);
		logger.info(command.getValue());

		if (command != null && command.getValue() != null) {
			try {
				console.execute(command.getValue());
			} catch (RoverException e) {
				// Ignoring exception
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		model.addObject("history", console.getHistory());

		return model;
	}
}
