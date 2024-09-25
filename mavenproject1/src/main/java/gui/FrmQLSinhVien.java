package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.QLSinhVien;
import model.SinhVien;

/**
 *
 * @author ADMIN
 */
public class FrmQLSinhVien extends JFrame {

    private JTable tblSinhVien;
    private JButton btThem, btXoa;
    private JButton btDocFile, btGhiFile;

    private DefaultTableModel model;
    private JTextField txtMaSo, txtHoTen, txtDTB;

    private JRadioButton rdNam, rdNu;
    private JCheckBox chkSapXep;

    private static final String FILE_NAME = "data.txt";

    private QLSinhVien qlsv = new QLSinhVien();

    public FrmQLSinhVien(String title) {
        super(title);
        createGUI();
        processEvent();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createGUI() {

        //tạo JTable
        String[] columnNames = {"Mã số", "Họ tên", "Phái", "ĐTB", "Học Lực"};
        model = new DefaultTableModel(null, columnNames);
        tblSinhVien = new JTable(model);
        //tạo thành phần quản lý cuộn cho Jtable
        JScrollPane scrollTable = new JScrollPane(tblSinhVien);

        //tạo các điều khiển nhập liệu  và các nút lệnh
        JPanel p = new JPanel();
        p.add(new JLabel("Mã sinh viên"));
        p.add(txtMaSo = new JTextField(5));
        p.add(new JLabel("Họ tên"));
        p.add(txtHoTen = new JTextField(10));

        p.add(rdNam = new JRadioButton("Nam"));
        p.add(rdNu = new JRadioButton("Nữ"));

        ButtonGroup btgPhai = new ButtonGroup();
        btgPhai.add(rdNam);
        btgPhai.add(rdNu);

        p.add(new JLabel("Điểm TB"));
        p.add(txtDTB = new JTextField(10));

        p.add(btDocFile = new JButton("Đọc File"));
        p.add(btThem = new JButton("Thêm"));
        p.add(btXoa = new JButton("Xoá"));
        p.add(btGhiFile = new JButton("Ghi File"));

        JPanel p2 = new JPanel();
        p2.add(chkSapXep = new JCheckBox("Sắp xếp theo học lực"));

        //add các thành phần vào cửa sổ
        add(p, BorderLayout.NORTH);
        add(scrollTable, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);
    }

    private void processEvent() {

        btDocFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                qlsv.DocDanhSachSinhVien(FILE_NAME);
                loadDataToJTable();
            }
        });

        btThem.addActionListener((e) -> {

            String error = "";
            if (txtMaSo.getText().length() == 0) {
                error = "Chưa nhập Thông tin sinh viên";
            }

            if (error.length() > 0) {
                JOptionPane.showMessageDialog(this, error, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //tạo đối tượng sinh viên
            SinhVien sv = new SinhVien();
            sv.setMaso(txtMaSo.getText());
            sv.setHoten(txtHoTen.getText());
            sv.setGioitinh(rdNam.isSelected());
            sv.setDiemTB(Double.parseDouble(txtDTB.getText()));

            if (qlsv.themSV(sv)) {
                loadDataToJTable();
                JOptionPane.showMessageDialog(this, "Thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Thao tác thêm thất bại do trùng mã sinh viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        //xu ly nut xoa
        btXoa.addActionListener((e) -> {

            //lấy chỉ số dòng được chọn trong JTable
            int selectedRowIndex = tblSinhVien.getSelectedRow();
            if (selectedRowIndex >= 0) {
                if (JOptionPane.showConfirmDialog(this, "Có chắc muốn sinh viên này không?") == JOptionPane.YES_OPTION) {
                    //lấy mã số sinh viên tại dòng được chọn
                    String maso = tblSinhVien.getValueAt(selectedRowIndex, 0).toString();
                    //System.out.println(maso);
                    qlsv.xoaSV(maso);
                    loadDataToJTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn sinh viên cần xoá");
            }
        });

        //xu ly ghi danh sach sinh vien ra file
        btGhiFile.addActionListener((e) -> {

            if (qlsv.GhiDanhSachSinhVien(FILE_NAME)) {
                JOptionPane.showMessageDialog(this, "Ghi file thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Ghi file thât bại");
            }

        });

        //xu ly sap xep
        chkSapXep.addItemListener((e) -> {
            if (chkSapXep.isSelected()) {
                qlsv.sapXepTheoHocLuc();
                loadDataToJTable();
            }
        });
    }

    private void loadDataToJTable() {
        model.setRowCount(0);  //xoá các dòng trong JTable        
        for (SinhVien sv : qlsv.getDsSinhVien()) {
            model.addRow(new Object[]{sv.getMaso(), sv.getHoten(), sv.isGioitinh() == true ? "Nam" : "Nữ", sv.getDiemTB(), sv.getHocLuc()});
        }
    }

}
