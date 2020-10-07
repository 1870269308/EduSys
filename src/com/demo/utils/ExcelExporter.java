package com.demo.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.TableModel;
//导出Excel类
public class ExcelExporter {
	/**导出JTable到excel */
    public void exportTable(JTable table, File file) throws IOException {
    	//创建一个表格模型
        TableModel model = table.getModel();
        //字符输出流
        BufferedWriter bWriter = new BufferedWriter(new FileWriter(file)); 
        //getColumnCount()-返回此数据表中的列数。
        for(int i=0; i < model.getColumnCount(); i++) {
        	//写入
            bWriter.write(model.getColumnName(i));
            bWriter.write("\t");
        }
        //newLine() 写入一个行分隔符
        bWriter.newLine();
        //getRowCount()- 返回此数据表中的行数
        for(int i=0; i< model.getRowCount(); i++) {
            for(int j=0; j < model.getColumnCount(); j++) {
            	//获取到某行某列的值，并写入字符输出流
            	//非空判断
				if(model.getValueAt(i, j)==null) {
					bWriter.write("\t");
				}else {
                	bWriter.write(model.getValueAt(i,j).toString());
                	bWriter.write("\t");
                }	
            }
            bWriter.newLine();
        }
        bWriter.close();
    }
}
