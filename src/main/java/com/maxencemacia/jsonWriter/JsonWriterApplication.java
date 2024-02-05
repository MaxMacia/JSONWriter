package com.maxencemacia.jsonWriter;

import com.maxencemacia.jsonWriter.view.View;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class JsonWriterApplication {

	public static void main(String[] args) {
		View view = new View();

		Component[] modelButtons = view.getModelListContainer().getComponents();
		for (Component component : modelButtons) {
			if (component instanceof JButton button) {
				button.addActionListener(view.getDisplayModelController());
			}
		}
		view.getAddModel().addActionListener(view.getDisplayAskFoAttributesNbFormController());
		view.getValidateAttributeNb().addActionListener(view.getDisplayAddModelFormController());
		view.getValidateAttrbutes().addActionListener(view.getAddModelController());
	}

}
