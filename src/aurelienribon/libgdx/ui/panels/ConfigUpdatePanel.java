package aurelienribon.libgdx.ui.panels;

import aurelienribon.libgdx.ui.Ctx;
import aurelienribon.libgdx.ui.MainPanel;
import aurelienribon.ui.css.Style;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class ConfigUpdatePanel extends javax.swing.JPanel {
	private boolean clicToShowSettings = true;

    public ConfigUpdatePanel(final MainPanel mainPanel) {
        initComponents();

		browseBtn.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {browse();}});

		advancedSettingsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		advancedSettingsLabel.addMouseListener(new MouseAdapter() {
			@Override public void mousePressed(MouseEvent e) {
				if (clicToShowSettings) {
					mainPanel.showAdvancedSettings();
					advancedSettingsLabel.setText("< Hide advanced settings");
				} else {
					mainPanel.hideAdvancedSettings();
					advancedSettingsLabel.setText("Show advanced settings >");
				}
				clicToShowSettings = !clicToShowSettings;
			}
		});

		Style.registerCssClasses(headerPanel, ".header");
		Style.registerCssClasses(numberLabel, ".headerNumber");
		Style.registerCssClasses(advancedSettingsLabel, ".linkLabel");
		Style.registerCssClasses(statusCoreLabel, ".statusLabel");
		Style.registerCssClasses(statusAndroidLabel, ".statusLabel");
		Style.registerCssClasses(statusDesktopLabel, ".statusLabel");
		Style.registerCssClasses(statusHtmlLabel, ".statusLabel");
    }

	private void browse() {
		String path = Ctx.cfgUpdate.destinationPath;
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

		JFileChooser chooser = new JFileChooser(new File(path));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("Select the core project folder");

		if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			pathField.setText(chooser.getSelectedFile().getPath());
			update(chooser.getSelectedFile());
			Ctx.fireCfgUpdateChanged();
		}
	}

	private void update(File coreDir) {
		Ctx.cfgUpdate.destinationPath = coreDir.getParent();
		Ctx.cfgUpdate.projectName = "";

		statusCoreLabel.firePropertyChange("found", true, false);
		statusAndroidLabel.firePropertyChange("found", true, false);
		statusDesktopLabel.firePropertyChange("found", true, false);
		statusHtmlLabel.firePropertyChange("found", true, false);
		statusCoreLabel.firePropertyChange("notfound", false, true);
		statusAndroidLabel.firePropertyChange("notfound", false, true);
		statusDesktopLabel.firePropertyChange("notfound", false, true);
		statusHtmlLabel.firePropertyChange("notfound", false, true);

		String dirName = coreDir.getName();
		if (dirName.endsWith(Ctx.cfgUpdate.suffixCommon)) {
			dirName = dirName.substring(0, dirName.length() - Ctx.cfgUpdate.suffixCommon.length());
		} else {
			return;
		}

		if (!new File(coreDir, ".classpath").isFile()) {
			return;
		}

		Ctx.cfgUpdate.projectName = dirName;
		statusCoreLabel.firePropertyChange("found", false, true);

		Ctx.cfgUpdate.isAndroidIncluded = false;
		Ctx.cfgUpdate.isDesktopIncluded = false;
		Ctx.cfgUpdate.isHtmlIncluded = false;

		for (File dir : new File(Ctx.cfgUpdate.destinationPath).listFiles()) {
			if (!dir.isDirectory()) continue;
			String name = dir.getName();

			if (name.equals(Ctx.cfgUpdate.projectName + Ctx.cfgUpdate.suffixAndroid) && new File(dir, ".classpath").isFile()) {
				Ctx.cfgUpdate.isAndroidIncluded = true;
				statusAndroidLabel.firePropertyChange("found", false, true);
			}

			if (name.equals(Ctx.cfgUpdate.projectName + Ctx.cfgUpdate.suffixDesktop) && new File(dir, ".classpath").isFile()) {
				Ctx.cfgUpdate.isDesktopIncluded = true;
				statusDesktopLabel.firePropertyChange("found", false, true);
			}

			if (name.equals(Ctx.cfgUpdate.projectName + Ctx.cfgUpdate.suffixHtml) && new File(dir, ".classpath").isFile()) {
				Ctx.cfgUpdate.isHtmlIncluded = true;
				statusHtmlLabel.firePropertyChange("found", false, true);
			}
		}
	}

	// -------------------------------------------------------------------------
	// Generated stuff
	// -------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        numberLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pathField = new javax.swing.JTextField();
        browseBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        advancedSettingsLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        statusCoreLabel = new javax.swing.JLabel();
        statusAndroidLabel = new javax.swing.JLabel();
        statusDesktopLabel = new javax.swing.JLabel();
        statusHtmlLabel = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jLabel4.setText("<html> Main parameters defining your project. See the overview panel to know if it suits your needs.");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        numberLabel.setText("2");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addComponent(numberLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(numberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(headerPanel, java.awt.BorderLayout.NORTH);

        jPanel1.setOpaque(false);

        pathField.setEditable(false);

        browseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/gfx/ic_browse.png"))); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Core project path");

        advancedSettingsLabel.setText("Show advanced settings >");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Core project");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Android project");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Desktop project");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Html project");

        statusCoreLabel.setText("---");

        statusAndroidLabel.setText("---");

        statusDesktopLabel.setText("---");

        statusHtmlLabel.setText("---");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(advancedSettingsLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel1))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pathField, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseBtn))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(statusAndroidLabel)
                                    .addComponent(statusDesktopLabel)
                                    .addComponent(statusHtmlLabel)
                                    .addComponent(statusCoreLabel))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel5, jLabel6});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseBtn))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(statusCoreLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(statusAndroidLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(statusDesktopLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(statusHtmlLabel))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(advancedSettingsLabel)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {browseBtn, jLabel3, pathField});

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel advancedSettingsLabel;
    private javax.swing.JButton browseBtn;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel numberLabel;
    private javax.swing.JTextField pathField;
    private javax.swing.JLabel statusAndroidLabel;
    private javax.swing.JLabel statusCoreLabel;
    private javax.swing.JLabel statusDesktopLabel;
    private javax.swing.JLabel statusHtmlLabel;
    // End of variables declaration//GEN-END:variables

}