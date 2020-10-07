package com.demo.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.TableModel;
//����Excel��
public class ExcelExporter {
	/**����JTable��excel */
    public void exportTable(JTable table, File file) throws IOException {
    	//����һ�����ģ��
        TableModel model = table.getModel();
        //�ַ������
        BufferedWriter bWriter = new BufferedWriter(new FileWriter(file)); 
        //getColumnCount()-���ش����ݱ��е�������
        for(int i=0; i < model.getColumnCount(); i++) {
        	//д��
            bWriter.write(model.getColumnName(i));
            bWriter.write("\t");
        }
        //newLine() д��һ���зָ���
        bWriter.newLine();
        //getRowCount()- ���ش����ݱ��е�����
        for(int i=0; i< model.getRowCount(); i++) {
            for(int j=0; j < model.getColumnCount(); j++) {
            	//��ȡ��ĳ��ĳ�е�ֵ����д���ַ������
            	//�ǿ��ж�
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
