package listaZakupow.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;



public class MainWindowController implements Initializable {


	    @FXML
	    private Button buttonAdd;

	    @FXML
	    private Button buttonRemove;
	    
	    @FXML
	    private Button buttonSend;

	    @FXML
	    private TextField textFieldThing;
	    
	    @FXML
	    private TextField textFieldQuantity;

	    @FXML
	    private TableView<Product> tabelView;

	    @FXML
	    private TableColumn<Product, String> tableThing;

	    @FXML
	    private TableColumn<Product, String> tableQuantity;
	    
	    int controlLine=0;
	    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    	 ObservableList<Product> products = getProducts();
    	 tableThing.setCellValueFactory(new PropertyValueFactory("thing"));
     	 tableQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
     	 tabelView.setItems(products);
    	//add
    	 buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
    	    @SuppressWarnings("unchecked")
		    @Override
    	    public void handle(ActionEvent event) {
    	    	 write();
    	     	 }  
    	    });
    	//remove
    	 buttonRemove.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				
				ObservableList<Product> selectedProduct, allProduct;
			 	allProduct = tabelView.getItems();
			 	selectedProduct = tabelView.getSelectionModel().getSelectedItems();
			 	selectedProduct.forEach(allProduct::remove);
			 	controlLine--;
			}	 
    	 });
    	 //send
    	 buttonSend.setOnAction(new EventHandler<ActionEvent>(){
     		public void handle(ActionEvent event) {
     			String message="";
     			for(int a=0;a<controlLine;a++)
     			{
     				message+=products.get(a).getThing()+"\t"+products.get(a).getQuantity()+"\n";	
     			}
     			
     		SendAnEmail send=new SendAnEmail();
     		send.setContent(message);
     		try {
				send.send();
			} catch (javax.mail.MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     	
     		}
     	});
    	 //keyboard support
    	 textFieldThing.setOnKeyPressed(event -> {
  		   if(event.getCode() == KeyCode.ENTER){
  		    textFieldQuantity.requestFocus();
  		   }
  		});     	 
    	 textFieldQuantity.setOnKeyPressed(event -> {
  		   if(event.getCode() == KeyCode.ENTER){
  			write();
  			textFieldThing.requestFocus();     
  		   } 		   
  		});  
    	
    }//end initialize
		public ObservableList<Product> getProducts() {
	      ObservableList<Product> products = FXCollections.observableArrayList();
	      return products;
	    }		
		public void write() {
	      Product product=new Product();
       	  product.setQuantity(textFieldQuantity.getText());
       	  product.setThing(textFieldThing.getText());
       	 
       	  if(!(product.getThing().equals(""))) {
       	  tabelView.getItems().add(product);
       	  textFieldQuantity.clear();
       	  textFieldThing.clear();
       	  controlLine++;  
       	  }
       	
		}
		
		
}

