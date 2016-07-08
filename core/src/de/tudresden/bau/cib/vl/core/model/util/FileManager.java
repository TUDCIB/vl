package de.tudresden.bau.cib.vl.core.model.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

/**
 * 
 * @deprecated will be replaced by {@link de.tudresden.bau.cib.vl.core.extension.service.FileService}.
 */
@Deprecated
public abstract class FileManager {

	
	private static final Logger LOG = LoggerFactory.getLogger(FileManager.class);
	
	/**
	 * @param directory
	 * @param fileFormat
	 * @param recursively <b>true</b>: find all files while searching all directories in the directory <br/>
	 * 					  <b>false</b>: search the files only in the folder with the name of the file format
	 * @return
	 */
	public static Set<File> getFiles(File directory, String fileFormat, boolean recursively) {
		LOG.trace("Retrieving files in directory: {} of file format: {} " + (recursively ? " with sub sub directories" :""), 
				new Object[]{directory.getAbsolutePath(), fileFormat});
		Set<File> result = new HashSet<File>();
		if(!recursively) { // no recursion
//			search only in folders with the name of the file extension
//			File searchDir = new File(directory+File.separator+fileFormat.toString());
			if(directory.exists()){
				File[] files = directory.listFiles();
				for(File file : files) {
					String fileName = file.getName();
					int lastDot = fileName.lastIndexOf(".")+1;
					String extension = fileName.substring(lastDot);
					if(extension.equalsIgnoreCase(fileFormat)) {
						result.add(file);
					}
				}
			}
		} else { //		with recursion - it searches all folders to find the files with the given file format
			if(directory.exists()){
				File[] files = directory.listFiles();
				if(files != null) {
					for(File file : files) {
						if(file.isDirectory()) {
							result.addAll(getFiles(file, fileFormat, recursively));
						} else {
							String fileName = file.getName();
							int lastDot = fileName.lastIndexOf(".")+1;
							String extension = fileName.substring(lastDot);
							if(extension.equalsIgnoreCase(fileFormat)) {
								result.add(file);
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	public static InputStream getZipFile(final File zipfileEntry) throws IOException {
		LOG.trace("Zipping file: {}", zipfileEntry);
        //we write to the PipedOutputStream
        //that data is then available in the PipedInputStream which we return
        final PipedOutputStream sink = new PipedOutputStream();
        PipedInputStream source = new PipedInputStream(sink);
        
        final int BUFFER = 2048;

        //apparently we need to write to the PipedOutputStream in a separate thread
        Runnable runnable = new Runnable() {
            public void run() {
                //PrintStream => BufferedOutputStream => ZipOutputStream => PipedOutputStream
                ZipOutputStream zip = new ZipOutputStream(sink);
                
                byte data[] = new byte[BUFFER];
                BufferedInputStream origin = null;
                try {
	                FileInputStream fin = new FileInputStream(zipfileEntry);
	                origin = new BufferedInputStream(fin, BUFFER);
	                ZipEntry entry = new ZipEntry(zipfileEntry.getName());
	                zip.putNextEntry(entry);
	                int count;
	                while((count = origin.read(data, 0, BUFFER)) != -1) {
	                	zip.write(data, 0, count);
	                }
	                origin.close();
	                
	                zip.close();
	            } catch(IOException e) {
	                LOG.error(e.getMessage(),e);	
                }
            }
        };
        Thread writerThread = new Thread(runnable, "FileGenerator");
        writerThread.start();
        return source;
	}
	
    /**
     * Taken from {@link http://zcox.wordpress.com/2009/08/26/dynamically-generating-zip-files-in-jersey/}
     * 
     * @param content
     * @return
     * @throws IOException
     */
    public static InputStream getZipFile(final String content, final String zipEntryFileName) throws IOException {
    	LOG.trace("Zipping file: {}",zipEntryFileName);
        //we write to the PipedOutputStream
        //that data is then available in the PipedInputStream which we return
        final PipedOutputStream sink = new PipedOutputStream();
        PipedInputStream source = new PipedInputStream(sink);

        //apparently we need to write to the PipedOutputStream in a separate thread
        Runnable runnable = new Runnable() {
            public void run() {
                //PrintStream => BufferedOutputStream => ZipOutputStream => PipedOutputStream
                ZipOutputStream zip = new ZipOutputStream(sink);
                PrintStream writer = new PrintStream(new BufferedOutputStream(zip));

                try {
                    zip.putNextEntry(new ZipEntry(zipEntryFileName));
                    writer.println(content);
                    writer.flush();
                    zip.closeEntry();
                }
                catch (IOException e) {
                	LOG.error(e.getMessage(), e);
                }
                writer.flush();
                writer.close();
            }
        };
        Thread writerThread = new Thread(runnable, "FileGenerator");
        writerThread.start();
        return source;
    }
    
    /**
     * Taken from {@link http://www.java-examples.com/create-zip-file-multiple-files-using-zipoutputstream-example}
     * @param zipFilePath
     * @param filesToZip
     * @throws IOException
     */
    public static void zip(String zipFilePath, File[] filesToZip) throws IOException {
    	byte[] buffer = new byte[1024];
        FileOutputStream fout = new FileOutputStream(zipFilePath);
         
        ZipOutputStream zout = new ZipOutputStream(fout);
         
        for(int i=0; i < filesToZip.length; i++) {
        	LOG.trace("Adding: {}",filesToZip[i]);
        	//create object of FileInputStream for source file
            FileInputStream fin = new FileInputStream(filesToZip[i]);
            zout.putNextEntry(new ZipEntry(filesToZip[i].getName()));
            int length;

            while((length = fin.read(buffer)) > 0) {
            	zout.write(buffer, 0, length);
            }
            zout.closeEntry();
            fin.close();              
        }
        zout.close(); 
        LOG.trace("Zip file has been created!");
    }
	
	public static boolean createDirectory(File directory) {
		boolean isCreated = false;
		if(!directory.exists()) {
			isCreated = directory.mkdirs();
			LOG.trace("Directory: {} was "+(isCreated ? "" : "not")+" created successfully", directory.getAbsolutePath());
		} else {
			isCreated = true;
			LOG.trace("Directory: {} already exists", directory.getAbsolutePath());
		}
		return isCreated;
	}
	
	public static String createId(Integer userId) {
		long time = System.currentTimeMillis();
		String id = userId+"_"+time;
		return id;
	}
	
	/**
	 * Creates a GZip file and save it in the directory of the file
	 * @param file
	 * @throws IOException
	 */
	public static File gZip(File file) throws IOException {
		File gzippedFile = new File(file+".gz");
		int read = 0;
		byte[] data = new byte[1024];
		FileInputStream in = new FileInputStream(file);
		GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(gzippedFile));
		while((read = in.read(data, 0, 1024)) != -1) {
			out.write(data, 0, read);
		}
		in.close();
		out.close();	
		return gzippedFile;
	}
	
	public static String readFileToString(File file) throws IOException {
		return FileUtils.readFileToString(file);
	}
	
	/**
	 * Taken from {@link http://sloanseaman.com/wordpress/2012/05/22/tar-and-gzip-compression-in-java/}
	 * @param archivePath
	 * @param filesToTar
	 * @throws IOException
	 */
	public static void tarAndGzip(String archivePath, File[] filesToTar) throws IOException {
		if(!archivePath.endsWith(".tar.gz")) archivePath = archivePath.concat(".tar.gz");
		LOG.trace("Compressing: {} to: {}", new Object[]{filesToTar.length, archivePath});
        // Create the output stream for the output file
		FileOutputStream fos = new FileOutputStream(new File(archivePath));
        // Wrap the output file stream in streams that will tar and gzip everything
		TarArchiveOutputStream taos = new TarArchiveOutputStream(
				new GZIPOutputStream(new BufferedOutputStream(fos)));
        // TAR has an 8 gig file limit by default, this gets around that
		taos.setBigNumberMode(TarArchiveOutputStream.BIGNUMBER_STAR); // to get past the 8 gig limit
        // TAR originally didn't support long file names, so enable the support for it
		taos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
        // Get to putting all the files in the compressed output file
		for (File f : filesToTar) {
			addFilesToCompression(taos, f, ".");
		}
        // Close everything up
		taos.close();
		fos.close();
	}

	/**
	 * Taken from {@link http://sloanseaman.com/wordpress/2012/05/22/tar-and-gzip-compression-in-java/}
	 * Does the work of compression and going recursive for nested directories
	 * <p/>
	 * Borrowed heavily from http://www.thoughtspark.org/node/53
	 *
	 * @param taos The archive
	 * @param file The file to add to the archive
	 * @param dir The directory that should serve as the parent directory in the archive
	 * @throws IOException
	 */
	private static void addFilesToCompression(TarArchiveOutputStream taos, File file, String dir)
	    throws IOException {
	    // Create an entry for the file
	    taos.putArchiveEntry(new TarArchiveEntry(file, (dir.equals(".") ? file.getName() : dir+"/"+file.getName())));
	    if (file.isFile()) {
	    	// Add the file to the archive
	        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	        IOUtils.copy(bis, taos);
	        taos.closeArchiveEntry();
	        bis.close();
	    }
	    else if (file.isDirectory()) {
	        // close the archive entry
	        taos.closeArchiveEntry();
	        // go through all the files in the directory and using recursion, add them to the archive
	        for (File childFile : file.listFiles()) {
	            addFilesToCompression(taos, childFile, file.getName());
	        }
	    }
	}

	
	public static File unGZip(String archiveExportPath) throws IOException {
		LOG.trace("Unzipping: {}", archiveExportPath);
	    int read = 0;
	    byte[] data = new byte[1024];
		File f = new File(archiveExportPath);
		GZIPInputStream in = new GZIPInputStream(new FileInputStream(f));
		String name;
		if (archiveExportPath.endsWith(".gz")) {
		  name = archiveExportPath.substring(0, archiveExportPath.length()-3);
		} else {
		  name = archiveExportPath;
		}
		FileOutputStream out = new FileOutputStream(name);
		while((read = in.read(data, 0, 1024)) != -1)
			out.write(data, 0, read);
		in.close();
		out.close();
		
		return f;
	}
	
	public static boolean deleteFile(File file) {
		boolean isDeleted = file.delete();
		LOG.trace("Delete file: {} was "+(isDeleted ? "":" not ")+"successfully", file.getAbsolutePath());
		return isDeleted;
	}
	
	/**
	 * @param in
	 * @param outputFile
	 * @param appendToFile If false the file will be overwritten.
	 * @throws IOException
	 */
	public static void copyToFile(InputStream in, File outputFile, boolean appendToFile) throws IOException {   
		// write the inputStream to a FileOutputStream
		OutputStream out = new FileOutputStream(outputFile, appendToFile);

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = in.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}

		in.close();
		out.flush();
		out.close();

		LOG.trace("New file: {} created successfully", outputFile);
	}
	
	/**
	 * @param content
	 * @param outputFile
	 * @param appendToFile If false the file will be overwritten.
	 * @throws IOException
	 */
	public static void copyToFile(String content, File outputFile, boolean appendToFile) throws IOException {  
		FileWriter fileWriter = null;

		fileWriter = new FileWriter(outputFile, appendToFile);
		fileWriter.write(content);
		fileWriter.close();

		LOG.trace("File: {} created successfully", outputFile);
	}
	
	public static boolean createDirectories(File[] directories) {
		boolean areCreated = true;
		for(File dir : directories) {
			boolean isCreated = createDirectory(dir);
			LOG.trace("Directory: {} was "+(isCreated ? "" : "not")+" created successfully", dir.getAbsolutePath());
			areCreated &= isCreated;
		}
		return areCreated;
	}
	
	public static String getFileExtension(String fileNameOrFullPath) {
		int lastDot = fileNameOrFullPath.lastIndexOf(".");
		String type = fileNameOrFullPath.substring(lastDot+1);
		return type;
	}
	
	/**
	 * Get all files in the directory.
	 * @param directory
	 * @param recursively <b>true</b>: with sub directories
	 * @return
	 */
	public static Set<File> getFiles(File directory, boolean recursively) {
		LOG.trace("Retrieving files in directory: {} " + (recursively ? " with sub sub directories" :""), directory.getAbsolutePath());
		Set<File> result = new HashSet<File>();
		if(!recursively) { // no recursion
//			search only in folders with the name of the file extension
			if(directory.exists()){
				File[] files = directory.listFiles();
				for(File file : files) {
					if(!file.isDirectory()) {
						result.add(file);
					}
				}
			}
		} else { //		with recursion - it searches all folders to find all files
			if(directory.exists()){
				File[] files = directory.listFiles();
				for(File file : files) {
					if(file.isDirectory()) {
						result.addAll(getFiles(file, recursively));
					} else {
						result.add(file);
					}
				}
			}
		}
		return result;
	}
	
	public static boolean isPathEqual(String path, String compareTo){
		LOG.trace("Comparing path: {} to path: {}", new Object[]{path, compareTo});
		path = convertWindowsPathToUnix(path);
		compareTo = convertWindowsPathToUnix(compareTo);
		return path.equalsIgnoreCase(compareTo);
	}
	
	public static final String convertWindowsPathToUnix(String windowsPath) {
		String generalPath = windowsPath;
		return generalPath.replaceAll("\\\\", "/");
	}
	
	public static final String convertUnixPathToWindows(String unixPath) {
		String generalPath = unixPath;
		return generalPath.replaceAll("/", "\\\\");
	}
	
	/**
	 * Copies a directory to another file path with sub directories.
	 * @param fromPath
	 * @param parentPath
	 * @throws IOException
	 */
	public static void copyDirectory(String fromPath, String parentPath) throws IOException {
		copyDirectory(new File(fromPath), new File(parentPath));
	}
	
	/**
	 * Copies a directory to another file path with sub directories.
	 * @param directory
	 * @param parentDirectory
	 * @throws IOException
	 */
	public static void copyDirectory(File directory, File parentDirectory) throws IOException {
		FileUtils.copyDirectory(directory, parentDirectory);
	}
	
	public static void copyFile(String source, String destination) throws IOException {
		copyFile(new File(source), new File(destination));
	}
	
	/**
	 * Copies a file to a given target file.
	 * @param source
	 * @param destination
	 * @return
	 * @throws IOException
	 */
	public static String copyFile(File source, File destination) throws IOException {
		LOG.trace("Copy file from: {} to: {}", 
				new Object[]{source.getAbsolutePath(), destination.getAbsolutePath()});
		if(source.getAbsolutePath().equalsIgnoreCase(destination.getAbsolutePath())) {			
			String sourceContent = readFileToString(source);
			copyToFile(sourceContent, destination, false);
//			LOGGER.debug("File path: {} is not different with: {}", new Object[]{source, destination});
//			throw new IOException("Source and destination are the same files!");
		} else {
			Files.copy(source, destination);
		}
		return destination.getAbsolutePath();
	}
	
	/**
	 * @param file Also directory with sub files is possible.
	 * @param toDirectory
	 * @throws IOException
	 * @return new file path of file
	 */
	public static String copyFileToDirectory(File file, File toDirectory) throws IOException  {
		boolean isCreated = createDirectory(toDirectory);
		LOG.trace("Copy file: {} to directory: {}", 
				new Object[]{file.getAbsolutePath(), toDirectory.getAbsolutePath()});
		if(isCreated) {
			if(file.isDirectory()) { // is directory - create new directory at toDirectory and copy all sub files
				File[] subFiles = file.listFiles();
				String newDirPath = toDirectory.getAbsolutePath()+File.separator+file.getName();
				File newDir = new File(newDirPath);
				boolean isCreated2 = createDirectory(newDir);
				if(isCreated2) {
					copyFilesToDirectory(subFiles, newDir); // recursion
					return newDir.getAbsolutePath();
				}
			} else { // no directory		
				String fileName = file.getName();
				String directoryPath = toDirectory.getAbsolutePath()+File.separator;
				return copyFile(file, new File(directoryPath+fileName));
			}
		}
		return null;
	}
	
	/**
	 * @param files Also directories with sub files are possible.
	 * @param toDirectory
	 * @throws IOException
	 */
	public static void copyFilesToDirectory(File[] files, File toDirectory) throws IOException  {
		for(File f : files) {
			copyFileToDirectory(f, toDirectory);
		}
	}
}
