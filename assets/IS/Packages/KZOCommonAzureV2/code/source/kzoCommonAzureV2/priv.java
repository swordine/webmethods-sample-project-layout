package kzoCommonAzureV2;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.util.JournalLogger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.FeedOptions;
// --- <<IS-END-IMPORTS>> ---

public final class priv

{
	// ---( internal utility methods )---

	final static priv _instance = new priv();

	static priv _newInstance() { return new priv(); }

	static priv _cast(Object o) { return (priv)o; }

	// ---( server methods )---




	public static final void setConnection (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(setConnection)>> ---
		// @sigtype java 3.5
		String url = "https://aeuwkenzoomnidevdbserv01.documents.azure.com";
		String pwd = "r4mtJU78IzAsFUq7QCvKxZvkCsS3PI3aMPP5vLeVksbZNfpuEs4JBXQQhHP4jZKK54HsPEWJ1DisaT0JnMAImQ==";
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		String idCustomer;
		
		DocumentClient client;
		
		ConnectionPolicy cp = new ConnectionPolicy();
		//cp.setConnectionMode(ConnectionMode.DirectHttps);
		
		JournalLogger.log(4,90,3,"[FRMK AZURE 2]", "DEBUT");
		
		client = new DocumentClient(url, pwd, cp, ConsistencyLevel.Session);
		
		//client.get
		
		// as this is a multi collection enable cross partition query
		FeedOptions options = null;
		//options.setEnableCrossPartitionQuery(true);
		String collectionLink = "/dbs/aeuwkenzoomnidevdb01/colls/accounts";
		
		Iterator<Document> it = client.queryDocuments(collectionLink, "SELECT * from accounts WHERE accounts.id ='KZ5582920000009'", options).getQueryIterator();
		
		int i = 0;
		while(it.hasNext()) {
		    Document d = it.next();
		
		    idCustomer = d.getId();
		    IDataUtil.put(pipelineCursor, "idCustomer", idCustomer);
		
		    i++;
		}
		
		//assertThat(i, equalTo(100));
		JournalLogger.log(4,90,3,"[FRMK AZURE 2]", "FIN");
		client.close();
		// --- <<IS-END>> ---

                
	}
}

