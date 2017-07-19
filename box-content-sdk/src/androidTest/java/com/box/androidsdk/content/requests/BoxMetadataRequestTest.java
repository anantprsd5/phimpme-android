package com.box.androidsdk.content.requests;

import com.box.androidsdk.content.BoxApiMetadata;

import junit.framework.TestCase;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;

/**
 * Created by ishay on 7/31/15.
 */
public class BoxMetadataRequestTest extends TestCase {

    public void testAddFileMetadataRequest() throws UnsupportedEncodingException {
        String expected = "{\"invoiceNumber\":\"12345\",\"companyName\":\"Boxers\",\"terms\":\"30\"}";

        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("invoiceNumber", "12345");
        map.put("companyName", "Boxers");
        map.put("terms", "30");
        BoxRequestsMetadata.AddFileMetadata request = (new BoxApiMetadata(null)).getAddFileMetadataRequest("fileId", map, "enterprise", "invoice");
        assertEquals(expected, request.getStringBody());
    }

    public void testUpdateFileMetadataRequest() throws UnsupportedEncodingException {
        String expected = "[{\"op\":\"test\",\"path\":\"/companyName\",\"value\":\"Boxers\"},{\"op\":\"remove\",\"path\":\"/companyName\"},{\"op\":\"replace\",\"path\":\"/terms\",\"value\":\"60\"},{\"op\":\"add\",\"path\":\"/approved\",\"value\":\"Yes\"}]";

        BoxRequestsMetadata.UpdateFileMetadata request = (new BoxApiMetadata(null)).getUpdateFileMetadataRequest("fileId", "enterprise", "invoice");
        request.addUpdateTask(BoxRequestsMetadata.UpdateFileMetadata.Operations.TEST, "companyName", "Boxers");
        request.addUpdateTask(BoxRequestsMetadata.UpdateFileMetadata.Operations.REMOVE, "companyName");
        request.addUpdateTask(BoxRequestsMetadata.UpdateFileMetadata.Operations.REPLACE, "terms", "60");
        request.addUpdateTask(BoxRequestsMetadata.UpdateFileMetadata.Operations.ADD, "approved", "Yes");
        assertEquals(expected, request.getStringBody());
    }


    public void testAddFolderMetadataRequest() throws UnsupportedEncodingException {
        String expected = "{\"invoiceNumber\":\"12345\",\"companyName\":\"Boxers\",\"terms\":\"30\"}";

        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("invoiceNumber", "12345");
        map.put("companyName", "Boxers");
        map.put("terms", "30");
        BoxRequestsMetadata.AddItemMetadata request = (new BoxApiMetadata(null)).getAddFolderMetadataRequest("folderId", map, "enterprise", "invoice");
        assertEquals(expected, request.getStringBody());
    }

    public void testUpdateFolderMetadataRequest() throws UnsupportedEncodingException {
        String expected = "[{\"op\":\"test\",\"path\":\"/companyName\",\"value\":\"Boxers\"},{\"op\":\"remove\",\"path\":\"/companyName\"},{\"op\":\"replace\",\"path\":\"/terms\",\"value\":\"60\"},{\"op\":\"add\",\"path\":\"/approved\",\"value\":\"Yes\"}]";

        BoxRequestsMetadata.UpdateItemMetadata request = (new BoxApiMetadata(null)).getUpdateFolderMetadataRequest("folderId", "enterprise", "invoice");
        request.addUpdateTask(BoxRequestsMetadata.UpdateFileMetadata.Operations.TEST, "companyName", "Boxers");
        request.addUpdateTask(BoxRequestsMetadata.UpdateFileMetadata.Operations.REMOVE, "companyName");
        request.addUpdateTask(BoxRequestsMetadata.UpdateFileMetadata.Operations.REPLACE, "terms", "60");
        request.addUpdateTask(BoxRequestsMetadata.UpdateFileMetadata.Operations.ADD, "approved", "Yes");
           assertEquals(expected, request.getStringBody());
    }

}
