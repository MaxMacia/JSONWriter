package com.maxencemacia.jsonWriter;

import com.maxencemacia.jsonWriter.controller.DisplayAskFoAttributesNbFormController;
import com.maxencemacia.jsonWriter.controller.DisplayModelController;
import com.maxencemacia.jsonWriter.view.View;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class JsonWriterApplication {

	public static void main(String[] args) {
		View view = new View();
		DisplayModelController displayModelController = new DisplayModelController(view);
		DisplayAskFoAttributesNbFormController displayAskFoAttributesNbFormController = new DisplayAskFoAttributesNbFormController(view);

		Component[] modelButtons = view.getModelListContainer().getComponents();
		for (Component component : modelButtons) {
			if (component instanceof JButton button) {
				button.addActionListener(displayModelController);
			}
		}
		view.getAddModel().addActionListener(displayAskFoAttributesNbFormController);
	}

}
