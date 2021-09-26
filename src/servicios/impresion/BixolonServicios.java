package servicios.impresion;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tfhka.*;
import tfhka.ve.*;

public class BixolonServicios {
    final private Tfhka Impresora;
    private List<String> PortList;

    public BixolonServicios() {
        Impresora = new tfhka.ve.Tfhka();
        PortList = new ArrayList<>();
    }

    public boolean abrirPuerto(String puerto) {
        boolean Respuesta = false;
        System.out.println("Abriendo Puerto " +puerto);
        try {
            Respuesta = Impresora.OpenFpctrl(puerto);
            if (Respuesta) {
                System.out.println("Puerto " + puerto + " abierto.");

            } else {
                System.out.println("Error de Puerto " + puerto);

            }
        } catch (Exception e) {
            System.out.println("Error al abrir puerto "+e.getMessage());

        }
        return Respuesta;
    }

    public boolean CheckPrinter() {
        boolean Respuesta;

        Respuesta = Impresora.CheckFprinter();
        return Respuesta;
    }

    public void cerrarPuerto() {
        Impresora.CloseFpctrl();
    }

    private void btnObtZfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObtZfechaActionPerformed
        /*Output = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date1, date2;
        try
        {
            date1 = dateFormat.parse(jFormattedTextField5.getText());
            date2 = dateFormat.parse(jFormattedTextField6.getText());
            ReporteArray = Impresora.getZReport(date1,date2);
            localizador = "btnObtZfecha";
            loc_salida(localizador);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane,e);
        }*/
    }//GEN-LAST:event_btnObtZfechaActionPerformed

    private void cmbPortListPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmbPortListPopupMenuWillBecomeVisible
        String[] puertos = jssc.SerialPortList.getPortNames();
        PortList.clear();
        for (String puerto : puertos) {
            PortList.add(puerto);
        }
    }


}

