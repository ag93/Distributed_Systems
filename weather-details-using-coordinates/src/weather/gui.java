/*
Name : Aniket Gade
UTA ID : 10010505046

References:
1) https://graphical.weather.gov/xml/
2) https://stackoverflow.com/questions/2811001/how-to-read-xml-using-xpath-in-java
3) https://stackoverflow.com/questions/15940234/how-to-do-a-soap-web-service-call-from-java-class
4) https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
5) https://www.tutorialspoint.com/java/math/bigdecimal_valueof_double.htm 
 */
package weather;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.io.ByteArrayInputStream;
import gov.weather.graphical.xml.DWMLgen.schema.DWML_xsd.*;
import gov.weather.graphical.xml.DWMLgen.wsdl.ndfdXML_wsdl.*;

/**
 *
 * @author Aniket Gade
 * This class is used to create the GUI, take input, connect to the 
 */
public class gui extends javax.swing.JFrame {
    double latitude, longitude; // input parameters
    String result;  //A variable to store the xml result
    String temperature, cloud, precipitation, speed, wind_direction;//Strings to hold respective data
    /**
     * Creates new form GUI
     */
    public gui() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ip_long = new javax.swing.JTextField();
        ip_lat = new javax.swing.JTextField();
        btn_Submit = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        op_cloud = new javax.swing.JLabel();
        op_humidity = new javax.swing.JLabel();
        op_temp = new javax.swing.JLabel();
        op_precipitation = new javax.swing.JLabel();
        op_speed = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        op_temp1 = new javax.swing.JLabel();
        op_temp2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" Weather Client Using XML");

        jLabel2.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        jLabel2.setText("Longitude");

        jLabel3.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        jLabel3.setText("Latitude");

        btn_Submit.setText("Submit");
        btn_Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SubmitActionPerformed(evt);
            }
        });

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(466, 466, 466)
                        .addComponent(jButton1)
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ip_lat)
                                    .addComponent(ip_long, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Submit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addGap(103, 103, 103)
                                .addComponent(jButton3))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(op_temp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(op_precipitation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(op_humidity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(op_speed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(op_temp1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(op_cloud, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(op_temp2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ip_lat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ip_long, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Submit)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(op_temp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(op_temp1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(op_temp2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(op_cloud, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(op_precipitation, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(op_speed, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(op_humidity, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SubmitActionPerformed
        latitude = Double.parseDouble(ip_lat.getText()); //Take latitude from GUI
        longitude = Double.parseDouble(ip_long.getText()); //Take longitude from GUI
        
        
        try{
            /*
            This block of code is used for setting up the connection and requesting the xml containing the weather info. 
            Once request is accepted and xml is recieved, it stores is in a string 'result'.
            */
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            URL url = new URL("https://graphical.weather.gov:443/xml/SOAP_server/ndfdXMLserver.php");  //variable used to store end point url of the web service
            NdfdXMLBindingStub stub = new NdfdXMLBindingStub(url,null);  //Instantiating the instance where the NDFDgen method is implemented which is used for calling the web service by passing the url
            ProductType product = ProductType.fromValue("time-series");  //An instance of the class ProductType which is used for calling NDFDgen and retrieving the product object 
            UnitType unit = UnitType.fromValue("m");  //retrieving the unit object 
            WeatherParametersType weatherParameters = new WeatherParametersType();  //instantiating the weatherParameters object
            Date date = new Date(System.currentTimeMillis());  //To get the current date and time 
            Calendar time = Calendar.getInstance();  //Creating a Calendar instance
            time.setTime(date);  //Setting the calendar instance to current time
            result = stub.NDFDgen(BigDecimal.valueOf(latitude), BigDecimal.valueOf(longitude), product, time, time, unit, weatherParameters);          
            //NDFDgen method is called by passing all the required parameters. 
            //This method returns an xml which has all the weather information
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

//            This block of code is used for parsing the xml using DocumentBuilderFactory and  xmlStringBuilder
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringBuilder xmlStringBuilder = new StringBuilder();
            xmlStringBuilder.append(result);  //Appending the xml result to string builder
            //op.setText(result); //Display the XML File on the GUI
            ByteArrayInputStream input =  new ByteArrayInputStream(xmlStringBuilder.toString().getBytes());
            Document doc = builder.parse(input);  //parsing the input stream
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------                      
//          This block of code is used for printing the result on the GUI
            temperature = doc.getElementsByTagName("temperature").item(0).getTextContent();  //Extracting temperature parameter
            op_temp1.setText(temperature + "degrees C");  //Print to the GUI Label
            
            temperature = doc.getElementsByTagName("temperature").item(1).getTextContent();  //Extracting Minimum temperature parameter
            op_temp.setText(temperature + "degrees C");  //Print to the GUI Label
            
            temperature = doc.getElementsByTagName("temperature").item(2).getTextContent();  //Dew Point temperature parameter
            op_temp2.setText(temperature + "degrees C");  //Print to the GUI Label
            
            cloud = doc.getElementsByTagName("cloud-amount").item(0).getTextContent();  //Extracting cloud-amount parameter
            op_cloud.setText(cloud + "percent");  //Print to the GUI Label
            
            precipitation = doc.getElementsByTagName("probability-of-precipitation").item(0).getTextContent();  //Extracting probability-of-precipitation parameter
            op_precipitation.setText(precipitation + "percent");  //Print to the GUI Label
            
            speed = doc.getElementsByTagName("wind-speed").item(0).getTextContent();  //Extracting wind-speed parameter
            op_speed.setText(speed + "knots");  //Print to the GUI Label
            
            wind_direction = doc.getElementsByTagName("direction").item(0).getTextContent();  //Extracting wind-direction parameter
            op_humidity.setText(wind_direction + "degrees");  //Print to the GUI Label
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------             
        }
        
        catch(Exception e)
        {
             System.out.println(e);
             op_temp.setText(e.toString()); //Display Exception on GUI
        }
        
        
    }//GEN-LAST:event_btn_SubmitActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        btn_SubmitActionPerformed(null);
    }//GEN-LAST:event_jButton2ActionPerformed
/*
        This function is used to clear all the fields.
*/
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //ip_lat.setText("");
        //ip_long.setText("");
        op_cloud.setText("");
        op_humidity.setText("");
        op_precipitation.setText("");
        op_speed.setText("");
        op_temp.setText("");
        op_temp1.setText("");
        op_temp2.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Submit;
    private javax.swing.JTextField ip_lat;
    private javax.swing.JTextField ip_long;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel op_cloud;
    private javax.swing.JLabel op_humidity;
    private javax.swing.JLabel op_precipitation;
    private javax.swing.JLabel op_speed;
    private javax.swing.JLabel op_temp;
    private javax.swing.JLabel op_temp1;
    private javax.swing.JLabel op_temp2;
    // End of variables declaration//GEN-END:variables
}
