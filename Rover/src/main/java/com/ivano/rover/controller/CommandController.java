package com.ivano.rover.controller;

import java.util.ArrayList;
import java.util.List;

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

	private List<String> history = new ArrayList<String>();

	@RequestMapping(value = "/")
	public ModelAndView sendCommand(@ModelAttribute Command command) {

		ModelAndView model = new ModelAndView();
		model.setViewName("console");

		if (command != null && command.getValue() != null) {

			history.add("cmd : " + command.getValue());
			try {
				String result = console.execute(command.getValue());
				if (result != null) {
					history.add("res : " + result);
				}
			} catch (RoverException e) {
				// Ignoring exception
				logger.error(e.getMessage());
				e.printStackTrace();
				history.add("err : " + e.getMessage());
			}
		}
		model.addObject("history", history);

		model.addObject("command", new Command());

		return model;
	}
}
