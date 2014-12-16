
package particleswarm;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author saadel
 */
public class PersonalFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        
        if (f.isDirectory()) {
            return true;
        }
        String name = f.getName();
        String ext;
        int pointIndex = name.lastIndexOf(".");
        if (pointIndex == -1) {
            ext = null;
        }
        if(pointIndex == name.length()-1){
            ext = null;
        }
        ext = name.substring(pointIndex+1, name.length());
        
        if (ext == null) {
            return false;
        }
        
        if (ext.equals("csv")) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "csv Files (*.csv)";
    }
    
}
