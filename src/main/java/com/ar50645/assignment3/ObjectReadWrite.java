package com.ar50645.assignment3;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 * Library can be used to read and write to file
 */
public class ObjectReadWrite {

    private String file;
    private ObjectOutputStream writeStream;
    private ObjectInputStream readStream;

    public ObjectReadWrite(String file) {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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

    // Initialize ObjectOutputStream when file is empty
    private void initializesStreamWhenFileIsEmpty() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
        } catch (EOFException e) {
            writeStream = new ObjectOutputStream(new FileOutputStream(file));
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean writeObject(Object e) throws IOException {
        writeStream.writeObject(e);
        return true;
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

    //Resets the file
    public void clearFile() throws IOException {
        FileWriter fileWriter = new FileWriter(file, false);
        PrintWriter printWriter = new PrintWriter(fileWriter, false);
        printWriter.flush();
        printWriter.close();
        fileWriter.close();
    }
}
