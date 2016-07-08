package de.tudresden.bau.cib.vl.core.service;

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
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.FileLocator;
import org.osgi.framework.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

import de.tudresden.bau.cib.vl.core.util.VirtualFile;

/**
 * Service with several file methods.
 * 
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public interface FileService {
	
	static Logger LOG = LoggerFactory.getLogger(FileService.class);
	
	
	/**
	 * @param directory The directory where the files are contained.
	 * @param fileName
	 * @return The file inside the directory.
	 * @throws MalformedURLException
	 */
	VirtualFile getVirtualFile(File directory, String fileName) throws MalformedURLException;
	
	
	/**
	 * @param file 	 *
	 * @return file as virtual file.
	 * @throws MalformedURLException
	 */
	VirtualFile getVirtualFile(File file) throws MalformedURLException;

	/**
	 * @param directory The directory where the files are contained.
	 * @param fileFormat The file format that is wanted. For example only XML files can be returned. 
	 * @param recursively If files in sub directories shall be included.
	 * @return The files of the directory and possible sub directories filtered by fileformat (all if null).
	 * @throws MalformedURLException
	 */
	Set<VirtualFile> getVirtualFiles(File directory, String fileFormat, boolean recursively) throws MalformedURLException;
	
	/**
	 * Same like {@link #getFiles(File, String, boolean)} but without filtering file formats.
	 * @param directory
	 * @param recursively
	 * @return
	 * @throws MalformedURLException
	 */
	Set<VirtualFile> getVirtualFiles(File directory, boolean recursively) throws MalformedURLException;
	
	/**
	 * Copies a file to a given target file.
	 * @param source
	 * @param destination
	 * @return
	 * @throws IOException
	 */
	public static String copyFile(File source, File destination) throws IOException {
		if(source.getAbsolutePath().equalsIgnoreCase(destination.getAbsolutePath())) {			
			String sourceContent = readFileToString(source);
			copyToFile(sourceContent, destination, false);
		} else {
			Files.copy(source, destination);
		}
		return destination.getAbsolutePath();
	}

	/**
	 * Create all directories if not already existing for the specified path (and access rights are given).
	 * @param path
	 */	
	static void createDirectories(String path) {
		createDirectories(new File(path));
	}
	
	/**
	 * Create all directories if not already existing for the specified path (and access rights are given).
	 * @param file
	 * @return 
	 */
	static boolean createDirectories(File directory) {
		boolean isCreated = false;
		if(!directory.exists()) {
			isCreated = directory.mkdirs();
			LOG.debug("Directory: {} was "+(isCreated ? "" : "not")+" created successfully", directory.getAbsolutePath());
		} else {
			isCreated = true;
			LOG.debug("Directory: {} already exists", directory.getAbsolutePath());
		}
		return isCreated;
	}
	
	/**
	 * Deletes a file and a directory(!). 
	 * @param file
	 * @return
	 */
	static boolean deleteFile(File file) {		
		boolean isDeleted = false;		
		if(file.isDirectory()) {
			try {
				FileUtils.deleteDirectory(file);
				isDeleted = true;
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
				isDeleted = false;
			}
		} else {
			isDeleted = file.delete();
		}			
		LOG.debug("Delete {}: {} was {}successfully", 
				new Object[]{file.isDirectory() ? "directory" : "file", file.getAbsolutePath(), (isDeleted ? "":" not ")});
		return isDeleted;
	}
	
	/**
	 * Deletes multiple files.
	 * @param files
	 * @return
	 */
	static boolean deleteFiles(File[] files) {
		boolean result = true;
		for(File f : files)
			result &= deleteFile(f);
		return result;
	}

	static void copyToFile(InputStream in, File outputFile, boolean appendToFile) throws IOException {   
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

		LOG.debug("New file: {} created successfully", outputFile);
	}

	/**
	 * Copies a file to a given target file.
	 * @param source
	 * @param destination
	 * @return
	 * @throws IOException
	 */
	static void copyFile(String source, String destination) throws IOException {
		copyFile(new File(source), new File(destination));
	}
	
	/**
	 * Copies a directory to another file path with sub directories.
	 * @param directory
	 * @param parentDirectory
	 * @throws IOException
	 */
	static void copyDirectory(File directory, File parentDirectory) throws IOException {
		FileUtils.copyDirectory(directory, parentDirectory);
	}

	/**
	 * Copies a directory to another file path with sub directories.
	 * @param fromPath
	 * @param parentPath
	 * @throws IOException
	 */
	static void copyDirectory(String fromPath, String parentPath) throws IOException {
		copyDirectory(new File(fromPath), new File(parentPath));
	}
	
	/**
	 * Useful method to copy resources from a bundle JAR to the local machine.
	 * @param relativeSrcFileUrl
	 * @param absolutTargetDir
	 * @param bundle
	 * @throws IOException
	 */
	static void copyFileFromBundle(URL relativeSrcFileUrl, String absolutTargetDir, Bundle bundle) throws IOException {
		if(bundle == null) {
	        String baseName = FilenameUtils.getBaseName(relativeSrcFileUrl.toString());
	        String extension = FilenameUtils.getExtension(relativeSrcFileUrl.toString());
			copyToFile(relativeSrcFileUrl.openStream(), new File(absolutTargetDir+File.separator+baseName+extension), false);
			return;
		}
        
		File file = new File(FileLocator.toFileURL(relativeSrcFileUrl).getPath());
		if(!file.isDirectory()) {
			String fileName = file.getName();
			
			File targetFile = new File(absolutTargetDir+File.separator+fileName);
			
			InputStream stream = relativeSrcFileUrl.openStream();
			copyToFile(stream, targetFile, false);
		} else {
			copyDirectoryFromBundle(relativeSrcFileUrl.getPath(), (absolutTargetDir+File.separator+file.getName()), bundle);
		}
	}
	
	/**
	 * Useful method to copy a directory from a bundle JAR to the local machine.
	 * @param srcDir
	 * @param targetDir
	 * @param bundle
	 * @throws IOException
	 */
	static void copyDirectoryFromBundle(String relativeSrcDir, String absolutTargetDir, Bundle bundle) throws IOException {
		if(bundle == null) {
			copyDirectory(relativeSrcDir, absolutTargetDir);
			return;
		}
		
		URL url = bundle.getResource(relativeSrcDir);
		File directory = new File(FileLocator.toFileURL(url).getPath());
		if(directory.isDirectory()) {			
			createDirectory(new File(absolutTargetDir));
			
			File[] files = directory.listFiles();
			for(File f : files) {
				String fileName = f.getName();
				URL fileUrl = bundle.getResource(relativeSrcDir+ (!(relativeSrcDir.endsWith("/")) ? "/" : "")+fileName);
				copyFileFromBundle(fileUrl, absolutTargetDir, bundle);
			}
		}
	}
	
	/**
	 * Converts a windows based file system path to a correspond unix path.
	 * @param windowsPath
	 * @return
	 */
	static String convertWindowsPathToUnix(String windowsPath) {
		String generalPath = windowsPath;
		return generalPath.replaceAll("\\\\", "/");
	}
	
	/**
	 * Converts a unix based file system path to a correspond windows path.
	 * @param unixPath
	 * @return
	 */
	static String convertUnixPathToWindows(String unixPath) {
		String generalPath = unixPath;
		return generalPath.replaceAll("/", "\\\\");
	}

	/**
	 * Checks if the paths are equal
	 * @param path
	 * @param compareTo
	 * @return
	 */
	static boolean isPathEqual(String path, String compareTo){
		LOG.debug("Comparing path: {} to path: {}", new Object[]{path, compareTo});
		path = convertWindowsPathToUnix(path);
		compareTo = convertWindowsPathToUnix(compareTo);
		return path.equalsIgnoreCase(compareTo);
	}

	/**
	 * Copies a file to a directory. If the directory doesn't exist it will be created.
	 * @param files Also directories with sub files are possible.
	 * @param toDirectory
	 * @throws IOException
	 */
	static void copyFilesToDirectory(File[] files, File toDirectory) throws IOException  {
		for(File f : files) {
			copyFileToDirectory(f, toDirectory);
		}
	}

	/**
	 * Copies a file to a directory. If the directory doesn't exist it will be created.
	 * @param file Also directory with sub files is possible.
	 * @param toDirectory
	 * @throws IOException
	 * @return new file path of file
	 */	
	static String copyFileToDirectory(File file, File toDirectory) throws IOException  {
		boolean isCreated = createDirectory(toDirectory);
		LOG.info("Copy file: {} to directory: {}", 
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
	 * Search a file recursively given by a file extension in a specific directory and returns the first match.
	 * @param directory The directory path
	 * @param extension The file extension after '.' e.g. 'txt' or 'xml'
	 * @return The file if found.
	 */
	static File findFirstFileByFileExtension(String directory, String extension) {	
		File inputDirectory = new File(directory);
		for (File file : inputDirectory.listFiles()) {
			if(file.isDirectory()) {
				File simModelFile = findFirstFileByFileExtension(file.getAbsolutePath(), extension);
				if(simModelFile != null) return simModelFile;
			}
			String fileName = file.getName();
			String compareExt = fileName.substring(fileName.lastIndexOf(".")+1);
			if(extension.equalsIgnoreCase(compareExt)) return file;
		}
		return null;
	}
	
	/**
	 * Search a file recursively by a given file name in a specific directory and returns the first match.
	 * @param directory The directory path
	 * @param fileName The file name e.g. 'file.xml'
	 * @return The file if found.
	 */
	static File findFirstFileByFileName(String directory, String fileName) {	
		File inputDirectory = new File(directory);
		for (File file : inputDirectory.listFiles()) {
			if(file.isDirectory()) {
				File simModelFile = findFirstFileByFileName(file.getAbsolutePath(), fileName);
				if(simModelFile != null) return simModelFile;
			}
			String cmpfileName = file.getName();
			if(fileName.equalsIgnoreCase(cmpfileName)) return file;
		}
		return null;
	}

	/**
	 * Get all files in the directory.
	 * @param directory
	 * @param recursively <b>true</b>: with sub directories
	 * @return
	 */	
	static Set<File> getFiles(File directory, boolean recursively) {
		LOG.debug("Retrieving files in directory: {} " + (recursively ? " with sub sub directories" :""), directory.getAbsolutePath());
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

	/**
	 * Gets the file extension of the file name or the full file path.
	 * @param fileNameOrFullPath
	 * @return
	 */
	static String getFileExtension(String fileNameOrFullPath) {
		int lastDot = fileNameOrFullPath.lastIndexOf(".");
		String type = fileNameOrFullPath.substring(lastDot+1);
		return type;
	}
	
	/**
	 * Checks the file extension of a file name.
	 * @param fileName Name or path of the file.
	 * @param extension The file extension.
	 * @return True if the given file extension was found.
	 */
	static boolean hasFileExtension(String fileName, String extension) {
		String fileExtension = getFileExtension(fileName);
		if(fileExtension.equalsIgnoreCase(extension)) return true;
		return false;
	}

	/**
	 * Create all directories if they do not exist.
	 * @param directories
	 * @return
	 */	
	static boolean createDirectories(File[] directories) {
		boolean areCreated = true;
		for(File dir : directories) {
			boolean isCreated = createDirectory(dir);
			LOG.debug("Directory: {} was "+(isCreated ? "" : "not")+" created successfully", dir.getAbsolutePath());
			areCreated &= isCreated;
		}
		return areCreated;
	}

	/**
	 * Copies the content to a file and appends it or overwrite it.
	 * @param content The file content to be appended or overwritten.
	 * @param outputFile The destination file.
	 * @param appendToFile True: appends the content to the end of the file, False: overwrites the content.
	 * @throws IOException
	 */
	static void copyToFile(String content, File outputFile, boolean appendToFile) throws IOException {  
		FileWriter fileWriter = null;

		fileWriter = new FileWriter(outputFile, appendToFile);
		fileWriter.write(content);
		fileWriter.close();
	}

	/**
	 * Unzip a GZip archive and returns the file in the archive.
	 * @param archiveExportPath
	 * @return
	 * @throws IOException
	 */
	static File unGZip(String archiveExportPath) throws IOException {
		LOG.debug("Unzipping: {}", archiveExportPath);
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
		while((read = in.read(data, 0, 1024)) != -1) {
			out.write(data, 0, read);
		}
		in.close();
		out.close();
		
		return f;
	}
	
	static List<File> unzip(String archiveFilePath, String outputDirectoryPath) throws IOException {
		InputStream in = new FileInputStream(archiveFilePath);
		return unzip(in, outputDirectoryPath);
	}
	
	static List<File> unzip(InputStream stream, String outputDirectoryPath) throws IOException {
		byte[] buffer = new byte[1024];
		//get the zip file content
    	ZipInputStream zis = new ZipInputStream(stream);
    	//get the zipped file list entry
    	ZipEntry ze = null;
    	List<File> unzippedFiles = new ArrayList<File>();
 
    	while((ze = zis.getNextEntry()) != null){
    	   String fileName = ze.getName();
           File newFile = new File(outputDirectoryPath + File.separator + fileName);
           // create all non exists folders
           if (ze.isDirectory()) {
        	   newFile.mkdirs();
        	   continue;
           } else {
        	   new File(newFile.getParent()).mkdirs();
           }
 
            FileOutputStream fos = new FileOutputStream(newFile);             
 
            int len;
            while ((len = zis.read(buffer)) > 0) {
            	fos.write(buffer, 0, len);
            }
            
            unzippedFiles.add(newFile);
 
            fos.close();   
//            ze = zis.getNextEntry();
    	}
 
        zis.closeEntry();
    	zis.close();
    	
    	File outputDirectory = new File(outputDirectoryPath);
    	if(!outputDirectory.exists()) throw new IOException("Directory: "+outputDirectoryPath+" was not created successfully");
		return unzippedFiles;
	}

	/**
	 * Taken from {@link http://sloanseaman.com/wordpress/2012/05/22/tar-and-gzip-compression-in-java/}
	 * @param archivePath
	 * @param filesToTar
	 * @throws IOException
	 */
	static void tarAndGzip(String archivePath, File[] filesToTar) throws IOException {
		if(!archivePath.endsWith(".tar.gz")) archivePath = archivePath.concat(".tar.gz");
		LOG.debug("Compressing: {} to: {}", new Object[]{filesToTar.length, archivePath});
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
	static void addFilesToCompression(TarArchiveOutputStream taos, File file, String dir)
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

	/**
	 * Returns the content of the file as string.
	 * @param file
	 * @return
	 * @throws IOException
	 */
	static String readFileToString(File file) throws IOException {
		return FileUtils.readFileToString(file);
	}

	/**
	 * Creates a GZip file and save it in the directory of the file
	 * @param file
	 * @throws IOException
	 */	
	static File gZip(File file) throws IOException {
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

	/**
	 * Creates a unique identifier by using the user identifier.
	 * @param userId
	 * @return An identifier with the following pattern: 	userId + System.currentTimeMillis();
	 */
	static String createId(Integer userId) {
		long time = System.currentTimeMillis();
		String id = userId+"_"+time;
		return id;
	}

	/**
	 * Creates the directory if not existent.
	 * @param directory
	 * @return
	 */
	static boolean createDirectory(File directory) {
		boolean isCreated = false;
		if(!directory.exists()) {
			isCreated = directory.mkdirs();
			LOG.info("Directory: {} was "+(isCreated ? "" : "not")+" created successfully", directory.getAbsolutePath());
		} else {
			isCreated = true;
			LOG.debug("Directory: {} already exists", directory.getAbsolutePath());
		}
		return isCreated;
	}

    /**
     * Taken from {@link http://www.java-examples.com/create-zip-file-multiple-files-using-zipoutputstream-example}
     * @param zipFilePath
     * @param filesToZip
     * @throws IOException
     */
    static void zip(String zipFilePath, File[] filesToZip) throws IOException {
    	byte[] buffer = new byte[1024];
        FileOutputStream fout = new FileOutputStream(zipFilePath);
         
        ZipOutputStream zout = new ZipOutputStream(fout);
         
        for(int i = 0; i < filesToZip.length; i++) {
        	File file = filesToZip[i];
        	if(file.isDirectory()) {
        		zip(file.getPath(), file.listFiles());
        		continue;
        	}
        	// create object of FileInputStream for source file
        	FileInputStream fin = null;
        	try {
	            fin = new FileInputStream(file);
	            zout.putNextEntry(new ZipEntry(file.getName()));
	            int length;
	
	            while((length = fin.read(buffer)) > 0) {
	            	zout.write(buffer, 0, length);
	            }
        	} finally {
	            zout.closeEntry();
	            fin.close();   
        	}
        }
        zout.close(); 
        LOG.debug("Zip file has been created!");
    }

    /**
     * Taken from {@link http://zcox.wordpress.com/2009/08/26/dynamically-generating-zip-files-in-jersey/}
     * 
     * @param content
     * @return
     * @throws IOException
     */
    static InputStream getZipFile(final String content, final String zipEntryFileName) throws IOException {
    	LOG.debug("Zipping file: {}",zipEntryFileName);
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
	 * Gets the stream of a zipped file entry.
	 * @param zipfileEntry
	 * @return
	 * @throws IOException
	 */	
	static InputStream getZipFile(final File zipfileEntry) throws IOException {
		LOG.debug("Zipping file: {}", zipfileEntry);
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
	 * @param directory
	 * @param fileFormat
	 * @param recursively <b>true</b>: find all files while searching all directories in the directory <br/>
	 * 					  <b>false</b>: search the files only in the folder with the name of the file format
	 * @return
	 */
	static Set<File> getFiles(File directory, String fileFormat, boolean recursively) {
		LOG.debug("Retrieving files in directory: {} of file format: {} " + (recursively ? " with sub sub directories" :""), 
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
					if(fileFormat == null || extension.equalsIgnoreCase(fileFormat)) {
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
							if(fileFormat == null || extension.equalsIgnoreCase(fileFormat)) {
								result.add(file);
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	String getTempDirectoryPath();

	/**
	 * Removes white spaces and accents from file name, 
	 * @param fileName
	 * @return A generalized file name
	 */
	static String convertFileName(String fileName) {
		String newFileName = new String(fileName.getBytes(), Charset.forName("UTF-8"));	
		newFileName = StringUtils.deleteWhitespace(newFileName);
		newFileName = StringUtils.stripAccents(newFileName);
		newFileName = newFileName.replaceAll("_", "");
		// remove '+'
		newFileName = newFileName.replaceAll("\\+", "-");
		return newFileName;
	}


	/**
	 * Zip an given directory and saves it to the given output path
	 * @param directory
	 * @param outputPath
	 * @throws IOException
	 */
    static void zipDir(File directory, String outputPath) throws IOException {
 	   //create a ZipOutputStream to zip the data to 
     ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outputPath)); 
     // assuming that there is a directory named inFolder (If there 
     // isn't create one) in the same directory as the one the code runs from, 
     // call the zipDir method 
 	String sourceFolder = directory.getAbsolutePath();	
	sourceFolder = sourceFolder.substring(sourceFolder.lastIndexOf(File.separator) + 1, sourceFolder.length());
		
     zipDir(directory, sourceFolder, zos); 
     //close the stream 
     zos.close();     
    }
    
    /**
     * Taken from {@link http://www.devx.com/tips/Tip/14049}
     * @param zipDir
     * @param sourceFolder
     * @param zos
     * @throws IOException
     */
    static void zipDir(File zipDir, String sourceFolder, ZipOutputStream zos) throws IOException {
    	//create a new File object based on the directory we have to zip File    
    	//get a listing of the directory content 
    	String[] dirList = zipDir.list(); 
    	byte[] readBuffer = new byte[1024]; 
    	int bytesIn = 0; 
    	//loop through dirList, and zip the files 
    	for(int i = 0; i < dirList.length; i++) { 
    		File f = new File(zipDir, dirList[i]); 
    		String name = sourceFolder+File.separator+f.getName();
    		if(f.isDirectory()) { 
    			//if the File object is a directory, call this 
    			//function again to add its content recursively 
    			zipDir(f, name, zos); 
    			//loop again 
    			continue; 
    		} 
    		//if we reached here, the File object f was not a directory 
    		//create a FileInputStream on top of f 
    		FileInputStream fis = new FileInputStream(f); 
    		// create a new zip entry 
    		ZipEntry anEntry = new ZipEntry(name); 
    		//place the zip entry in the ZipOutputStream object 
    		zos.putNextEntry(anEntry); 
    		//now write the content of the file to the ZipOutputStream 
    		while((bytesIn = fis.read(readBuffer)) != -1) { 
    			zos.write(readBuffer, 0, bytesIn); 
    		} 
    		//close the Stream 
    		fis.close(); 
    	}
    }

	static File findFile(String name, String bim2simProjectDirectoryPath, boolean recursively) {
		File parentDir = new File(bim2simProjectDirectoryPath);
		if(!parentDir.exists()) throw new IllegalArgumentException("Directory "+bim2simProjectDirectoryPath+" doesn't exist");
		for(File file : parentDir.listFiles()) {
			if(file.getName().equalsIgnoreCase(name)) return file;
		}
		if(recursively) {
			for(File file : parentDir.listFiles()) {
				if(file.isDirectory()) {
					File searchFile = findFile(name, file.getAbsolutePath(), recursively);
					if(searchFile != null) return searchFile;
				}
			}
		}
		return null;
	}

}
