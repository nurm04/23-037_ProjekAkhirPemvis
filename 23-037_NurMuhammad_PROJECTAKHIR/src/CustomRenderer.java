/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class CustomRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Warna default
        c.setBackground(Color.WHITE);
        c.setForeground(Color.BLACK);

        // Warna untuk kolom "Edit" (misalnya kolom ke-6)
        if (value != null && value.equals("Edit")) {
            c.setBackground(Color.YELLOW);
            c.setForeground(Color.BLACK);
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        // Warna untuk kolom "Hapus" (misalnya kolom ke-7)
        if (value != null && value.equals("Hapus")) {
            c.setBackground(Color.RED);
            c.setForeground(Color.WHITE);
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        // Warna saat baris terpilih
        if (isSelected) {
            c.setBackground(table.getSelectionBackground());
            c.setForeground(table.getSelectionForeground());
        }

        return c;
    }
    
    public static void customizeHeader(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(0xA1BEFF));
                c.setForeground(Color.BLACK);
                c.setFont(c.getFont().deriveFont(Font.BOLD, 12f));
                setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        });
    }
}

