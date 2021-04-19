import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

public class ServerDialog {

    private JDialog dialog;
    private JPanel panel;
    private JLabel addressLabel;
    private JLabel portLabel;
    private JTextField addressField;
    private JTextField portField;
    private JButton saveButton;
    private JButton cancelButton;
    private String address;
    private String port;

    public ServerDialog(){
        dialog = new javax.swing.JDialog();
        panel = new JPanel();
        addressLabel = new JLabel();
        portLabel = new JLabel();
        addressField = new JTextField();
        portField = new JTextField();
        saveButton = new JButton();
        cancelButton = new JButton();

        setDesign();

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void setDesign(){
        dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setTitle("Server Address");
        dialog.setBackground(new java.awt.Color(153, 153, 153));
        dialog.setModal(true);
        dialog.setResizable(false);

        panel.setBackground(new java.awt.Color(74, 74, 74));

        addressLabel.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        addressLabel.setForeground(new java.awt.Color(255, 255, 255));
        addressLabel.setText("Address");

        portLabel.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        portLabel.setForeground(new java.awt.Color(255, 255, 255));
        portLabel.setText("Port");

        addressField.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        addressField.setBorder(null);

        portField.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        portField.setBorder(null);

        saveButton.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        saveButton.setText("Save");
        saveButton.addActionListener((e) -> {
            String address = addressField.getText();
            String port = portField.getText();
            saveDetails(address, port);
        });

        cancelButton.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener((e) -> {
            dialog.dispose();
        });

        dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setTitle("Server Address");
        dialog.setBackground(new java.awt.Color(153, 153, 153));
        dialog.setModal(true);
        dialog.setResizable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(59, 59, 59)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(portLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(117, 117, 117)
                                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addressField, portField});

        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addressField, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                        .addComponent(addressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(portLabel)
                                        .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addressField, portField});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addressLabel, portLabel});

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(dialog.getContentPane());
        dialog.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
                jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
                jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
    
    private void saveDetails(String address, String port){
        try(FileWriter locFile = new FileWriter("address.txt")){
            locFile.write(address +"|"+port);
            try{
                Main.ct.connectionSocket.close();
            }catch (NullPointerException e){
                //do nothing as already closed
            }
            try {
                Main.ct.setAddress(address, Integer.parseInt(port));
            }catch (NumberFormatException e){
                Main.ct.setAddress("default", 0);
            }
            Main.ct.connectionSocket = new Socket(Main.ct.host, Main.ct.port);
            Main.data.setServerConnectionStatus(Main.ct.checkConnection());
            Main.home.setConnectionLabel();
            dialog.dispose();
        }catch (IOException e){
            System.out.println("saveDetails IOException");
            JOptionPane.showMessageDialog(null, "Error with server address!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
