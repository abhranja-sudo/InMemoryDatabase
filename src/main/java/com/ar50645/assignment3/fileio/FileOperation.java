package com.ar50645.assignment3.fileio;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Library can be used to read, write and clear the file
 */
public class FileOperation {

    private final String file;
    private ObjectOutputStream writeStream;
    private ObjectInputStream readStream;

    public FileOperation(String file) {
        this.file = file;
        initializeWriteStream();
        initializeReadStream();
    }


    private void initializeReadStream(){
        try {
            readStream = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Initializing ObjectOutputStream is tricky. If the OUT_FILE has no content in it, initialize it regular ObjectOutputStream,
     * otherwise you will get java.io.StreamCorruptedException on opening the file with ObjectInputStream.
     */
    private void initializeWriteStream() {

        try {
            initializesStreamWhenFileIsEmpty();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(writeStream == null) {
            try {
                writeStream = new ObjectOutputStream(new FileOutputStream(file, true)) {
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializesStreamWhenFileIsEmpty() throws IOException, ClassNotFoundException {
        try {
             new ObjectInputStream(new FileInputStream(file));
        } catch (EOFException e) {
            writeStream = new ObjectOutputStream(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeObject(Object e) {
        try {
            writeStream.writeObject(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return next object in file. return null when the pointer reaches EOF.
     */
    public Object readNext() {
        try {
            return readStream.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void clearFile(String file) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter printWriter = new PrintWriter(fileWriter, false);
        printWriter.flush();
        printWriter.close();

        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * copy contents from file1 to file2
     * @param file1 source file
     * @param file2 destination file
     */
    public static void copyFile(String file1, String file2) {
        clearFile(file2);

        try (FileChannel src = new FileInputStream(file1).getChannel()) {
            FileChannel dest = new FileOutputStream(file2).getChannel();
            dest.transferFrom(src, 0, src.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
