
import java.awt.Color;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.sql.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CreateEntry extends JFrame implements ActionListener {

    RandPass rp = new RandPass();
    String userSession;
    String userPassword;
    String name;
    String username;
    String password;
    String email;
    AESEncryption aes;

//    Dashboard db = new Dashboard(username);
    /**
     * Creates new form CreateEntry
     */
    public CreateEntry(String user, String password) {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setTitle("Passvault - Password Manager");
        userSession = user;
        userPassword = password;
        passwordField.setEchoChar((char) 0);
        aes = new AESEncryption(userPassword);
        actionEvent();

    }

    public void actionEvent() {
        genBtn.addActionListener(this);
        copyBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        saveBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == genBtn) {
            passwordField.setText(rp.randGen());
        }
        if (e.getSource() == copyBtn) {
            StringSelection stringSelection = new StringSelection(passwordField.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
        if (e.getSource() == cancelBtn) {
            this.dispose();
        }
        if (e.getSource() == saveBtn) {
            name = webName.getText();
            SHA1 s1 = new SHA1();
            String hashUsername = s1.hashMethod(userSession);
            try {
                username = aes.encrypt(usernameField.getText());
                password = aes.encrypt(passwordField.getText());
            } catch (Exception ex) {
                System.out.println(ex);
            }
            email = emailField.getText();
            System.out.print(password);
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mprdb", "root",
                        "ninadsql");
                // Preapared Statement
                PreparedStatement Pstatement = connection.prepareStatement("insert into " + hashUsername + "(webName, webUsername, URL, webPassword) values(?,?,?,?)");
                // Specifying the values of it's parameter
                Pstatement.setString(1, name);
                Pstatement.setString(2, username);
                Pstatement.setString(3, email);
                Pstatement.setString(4, password);
                Pstatement.executeUpdate();
                this.dispose();
//                refreshData rd = new refreshData();
                System.out.println("Saved Successfully");
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        }
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
        webName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        genBtn = new javax.swing.JButton();
        copyBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel1.setText("Name");

        webName.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel2.setText("Username");

        usernameField.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel3.setText("Password");

        passwordField.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        genBtn.setBackground(new java.awt.Color(38, 127, 255));
        genBtn.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        genBtn.setForeground(new java.awt.Color(255, 255, 255));
        genBtn.setText("Generate");
        genBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genBtnActionPerformed(evt);
            }
        });

        copyBtn.setBackground(new java.awt.Color(38, 127, 255));
        copyBtn.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        copyBtn.setForeground(new java.awt.Color(255, 255, 255));
        copyBtn.setText("Copy");
        copyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyBtnActionPerformed(evt);
            }
        });

        saveBtn.setBackground(new java.awt.Color(38, 127, 255));
        saveBtn.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        cancelBtn.setBackground(new java.awt.Color(38, 127, 255));
        cancelBtn.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel4.setText("URL");

        emailField.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveBtn)
                .addGap(18, 18, 18)
                .addComponent(cancelBtn)
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(genBtn)
                        .addGap(18, 18, 18)
                        .addComponent(copyBtn))
                    .addComponent(passwordField)
                    .addComponent(emailField)
                    .addComponent(usernameField)
                    .addComponent(webName)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(webName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genBtn)
                    .addComponent(copyBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn)
                    .addComponent(cancelBtn))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void genBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genBtnActionPerformed

    private void copyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_copyBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelBtnActionPerformed

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
            java.util.logging.Logger.getLogger(CreateEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CreateEntry ce1 = new CreateEntry(null, null);
                ce1.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton copyBtn;
    private javax.swing.JTextField emailField;
    private javax.swing.JButton genBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField usernameField;
    private javax.swing.JTextField webName;
    // End of variables declaration//GEN-END:variables
}

class RandPass {

    // Method to generate a random alphanumeric password of a specific length
    public static String randPassword(int len) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()_-+={[}]|:;'<,>.?";

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

    public static String randGen() {
        int len = 16;
        return (randPassword(len));
    }
}

//class refreshData extends Dashboard{
//    Dashboard(CreateEntry.user);
//    public refreshData(){
//        super.refreshData();
//    }
//}
