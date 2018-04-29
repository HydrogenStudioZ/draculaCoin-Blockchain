package application;

import java.net.URL;

import java.sql.*;

import blockChain.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.google.gson.GsonBuilder;

import blockChain.*;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable {

	String url="jdbc:mysql://localhost:3306/";
	String user="root";
	String pass="password";
	
	
	public static ArrayList<Block> Myblockchain;
	public int difficulty = 2;

	@FXML
	private Button myButton;

	@FXML
	private TextField myTextField;
	private TextField input;
	Scanner s1 = new Scanner(System.in);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void createBlockChain() {

		Myblockchain = new ArrayList<Block>();
		difficulty = 2;
		
		
		
		myTextField.setText("Blockchain created");

	}

	public void checkValidity() {

		myTextField.setText("Blockchain Validity " + BlockChain.isChainValid(Myblockchain));

	}

	public void genesisBlock() {

		myTextField.setText("Enter Genesis Data on above field");
		String temp = s1.nextLine();
		Myblockchain.add(new Block(temp, "0"));
		myTextField.setText("Now mining genesis block");

		Myblockchain.get(0).mineBlock(difficulty);
		myTextField.setText("genesis block mined");

	}

	public void newBlock() {
		String data = s1.nextLine();
		int previousindex = Myblockchain.size() - 1;
		Myblockchain.add(new Block(data, Myblockchain.get(previousindex).hash));
		System.out.println("Added block to blockchain");
		int index=Myblockchain.size()-1;
		Myblockchain.get(index).mineBlock(difficulty);
		myTextField.setText((index+1) + " block mined");

	}
	public void displayJson() {
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(Myblockchain);
		System.out.println(blockchainJson);
		myTextField.setText(blockchainJson);

	}
	
	public void createCoinBase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,user,pass);
			
			Statement s1=con.createStatement();
			
			s1.execute("CREATE DATABASE coinbase");
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	
	

}
