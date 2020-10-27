package com.flaringapp.data.storage;

import com.flaringapp.data.models.Text;

import java.io.FileNotFoundException;

public interface TextSourceModel {

    Text readText(String fileName) throws FileNotFoundException;

}
