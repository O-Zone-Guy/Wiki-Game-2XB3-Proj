/**
 @brief A module that provides a User Interface for the results of the application.
 @author Mike Tee - teemh
 @file UIResults.java
 @date 2020-04-09
 */

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class UIResults extends JPanel {

    // used to mark which rows need to be highlighted.
    public ArrayList<Integer> hi_rows = new ArrayList<>();

    public ArrayList<NodeT> nodes;

    // custom renderer to color cells.
    class CellRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            cell.setBackground(Color.white);

            if (hi_rows.contains(row))
            {
                cell.setBackground(new Color(220, 220, 220));
                if (column == 1) cell.setFont(new Font("Dialog", Font.BOLD, 12));
            }

            return cell;
        }
    }

    // draw the connected nodes
    class DrawPath extends JComponent
    {
        int max_w;
        int max_h;
        int pad;

        DrawPath(int width, int height, int padding)
        {
            max_w = width;
            max_h = height;
            pad = padding;
        }

        @Override
        public void paint(Graphics g) {
            FontMetrics f = g.getFontMetrics();

            int x = 0;
            int h = f.getHeight() + pad;
            int y = max_h / 2;

            for (NodeT node : nodes)
            {
                String name = node.getPageName();
                int w = f.stringWidth(name) + pad;
                g.setColor(Color.white);
                g.fillOval(x, y - h / 2, w, h);
                g.setColor(Color.black);
                g.drawOval(x, y - h / 2, w, h);
                g.drawString(name, x + pad / 2, y + pad / 6);
                g.drawLine(x - pad ,y,  x, y);
                x = x + w + pad;
            }
        }
    }

    public UIResults(ArrayList<NodeT> path) throws SQLException {
        nodes = path;

        JFrame frame = new JFrame();

        // frame title
        StringBuilder title = new StringBuilder();
        title.append("PATH ");
        for (NodeT node : nodes)
        {
            title.append(" - ").append(node.getPageName());
        }
        frame.setTitle(title.toString());

        // table header
        String[] head = { "Id", "Page", "Sorted Categories", "Sorted Neighbours" };

        DefaultTableModel model = new DefaultTableModel(head, 0);

        for(NodeT node : nodes)
        {
            int id = SQLHandler.getPageId(node.getPageName());

            ArrayList<String> categories = SQLHandler.getCategories(id);

            ArrayList<String> neighbours = new ArrayList<>();
            for (Integer nid : node.getNeighbours())
            {
                neighbours.add(SQLHandler.getPageName(nid));
            }

            // sort categories and neighbours
            // Collections.sort(categories);
            // Collections.sort(neighbours);
            Algorithms.sort(categories);
            Algorithms.sort(neighbours);

            // number of rows after new node depends on size of categories or neighbours.
            int rows = Math.max(neighbours.size(), categories.size());

            model.addRow(new String[] {Integer.toString(id), node.getPageName(), categories.get(0), neighbours.get(0)});
            hi_rows.add(model.getRowCount() - 1); // keep track of rows that need highlighting.

            // insert neighbours and categories.
            for (int i = 1; i < rows; i++)
            {
                String cat = "";
                String neigh = "";

                if (categories.size() > i) cat = categories.get(i);
                if (neighbours.size() > i) neigh = neighbours.get(i);

                model.addRow(new String[] {"", "|", cat, neigh});
            }
        }

        // width and height of the panel should match the preferred size.
        DrawPath top_panel = new DrawPath(800, 100, 30);
        top_panel.setPreferredSize(new Dimension(800, 100));

        JTable table = new JTable(model);

        // don't show table grid. It looks messy with all the empty cells.
        table.setShowGrid(false);

        // disallow row highlighting on cursor selected.
        table.setRowSelectionAllowed(false);

        // overrides the cell renderer with custom renderer to color cells.
        table.setDefaultRenderer(Object.class, new CellRenderer());

        JScrollPane table_panel = new JScrollPane(table);

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BorderLayout());
        main_panel.add(top_panel, BorderLayout.NORTH);
        main_panel.add(table_panel, BorderLayout.CENTER);

        frame.add(main_panel);
        frame.setSize(800, 600); // window dimentions
        frame.setVisible(true);

        // displays window in center of screen.
        frame.setLocationRelativeTo(null);
    }
}
