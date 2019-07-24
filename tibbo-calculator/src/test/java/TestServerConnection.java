import com.tibbo.Server;
import com.tibbo.ServerMessagesHelper;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class TestServerConnection extends TestCase {
    private Server server;

    @Test
    public void testServerConnection() throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 5555));
        DataOutputStream stream = new DataOutputStream(socket.getOutputStream());

        stream.writeUTF(ServerMessagesHelper.FIRST_MESSAGE);
        stream.flush();

        Socket socket1 = new Socket();
        socket1.connect(new InetSocketAddress("localhost", 5555));
        stream = new DataOutputStream(socket1.getOutputStream());
        stream.writeUTF(ServerMessagesHelper.SECOND_MESSAGE);
        stream.flush();

        Socket socket2 = new Socket();
        socket2.connect(new InetSocketAddress("localhost", 5555));

        stream = new DataOutputStream(socket2.getOutputStream());
        stream.writeUTF(ServerMessagesHelper.THIRD_MESSAGE);
        stream.flush();

        assertTrue(socket.isConnected());
        assertTrue(socket1.isConnected());
        assertTrue(socket2.isConnected());

        Thread.sleep(10000);
        assertEquals(3, server.getMessageCounter());

        socket.close();
        socket1.close();
        socket2.close();
    }

    @Test
    public void testCalculator() throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 5555));
        DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());

        assertTrue(socket.isConnected());

        outStream.writeUTF(ServerMessagesHelper.FIRST_MESSAGE);
        outStream.flush();

        String result = inputStream.readUTF();
        assertEquals(ServerMessagesHelper.MESSAGE_ERROR, result);

        outStream.writeUTF("2 * (2 + 2)");
        outStream.flush();

        result = inputStream.readUTF();
        assertEquals("8", result);

        outStream.writeUTF("(3 + 4) * 5");
        outStream.flush();

        result = inputStream.readUTF();
        assertEquals("35", result);

        outStream.writeUTF("2 ^ 2 ^ 2 ^ 2");
        outStream.flush();
        result = inputStream.readUTF();

        assertEquals("256", result);

        assertEquals(3, server.getMessageCounter());

        socket.close();
    }

    //тест 1
    //создать еще один тест, в котором будем вычислять
    //корень квадртный из 144
    //корень квадртный из 144 умножить на 200
    // корень квадрный из 4096 разделить на 8
    @Test
    public void testCalculatorSqrt() throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 5555));
        DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());

        assertTrue(socket.isConnected());

        outStream.writeUTF("sqrt(144)");
        outStream.flush();
        String result = inputStream.readUTF();
        assertEquals("12", result);

        outStream.writeUTF("sqrt(144)*200");
        outStream.flush();
        result = inputStream.readUTF();
        assertEquals("2400", result);

        outStream.writeUTF("sqrt(4096)/8");
        outStream.flush();
        result = inputStream.readUTF();
        assertEquals("32", result);

        assertEquals(3, server.getMessageCounter());
        socket.close();
    }


    //тест 2
    //посчитать корень квадртный из 9000(если не найдете корень, умножить PI на 200)
    //окргулить до 3 знака
    //результат умножить на 55.386
    //разделть на число 'e'
    //окргулить до целый часть(sign)
    @Test
    public void testCalculatorAdvanced() throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 5555));
        DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        assertTrue(socket.isConnected());

        outStream.writeUTF("sqrt(9000)");
        outStream.flush();
        String result = inputStream.readUTF();
        assertEquals("0", result);

        outStream.writeUTF("round(" + result+", 3)");
        outStream.flush();
        result = inputStream.readUTF();
        assertEquals("0", result);

        outStream.writeUTF(result+"*55.386");
        outStream.flush();
        result = inputStream.readUTF();
        assertEquals("0", result);

        outStream.writeUTF(result+"/#{e}");
        outStream.flush();
        result = inputStream.readUTF();
        assertEquals("0", result);

        outStream.writeUTF("sign("+result+")");
        outStream.flush();
        result = inputStream.readUTF();
        assertEquals("0", result);

        assertEquals(5, server.getMessageCounter());
        socket.close();
    }

    //тест 3
    //нужно создать несколько сокетов для клиента
    //вычислить в кажом по два выржания
    @Test
    public void testCalculatorMultiple() throws Exception {
        Socket socket1 = new Socket();
        Socket socket2 = new Socket();
        socket1.connect(new InetSocketAddress("localhost", 5555));
        socket2.connect(new InetSocketAddress("localhost", 5555));
        DataOutputStream outStream1 = new DataOutputStream(socket1.getOutputStream());
        DataInputStream inputStream1 = new DataInputStream(socket1.getInputStream());
        DataOutputStream outStream2 = new DataOutputStream(socket2.getOutputStream());
        DataInputStream inputStream2 = new DataInputStream(socket2.getInputStream());
        assertTrue(socket1.isConnected());
        assertTrue(socket2.isConnected());

        outStream1.writeUTF("sqrt(9000)");  //socket 1
        outStream1.flush();
        String result = inputStream1.readUTF();
        assertEquals("0", result);

        outStream1.writeUTF("round(" + result+", 3)");
        outStream1.flush();
        result = inputStream1.readUTF();
        assertEquals("0", result);

        assertEquals(2, server.getMessageCounter());
        socket1.close();

        outStream2.writeUTF(result+"*55.386");  //socket 2
        outStream2.flush();
        result = inputStream2.readUTF();
        assertEquals("0", result);

        outStream2.writeUTF(result+"/#{e}");
        outStream2.flush();
        result = inputStream2.readUTF();
        assertEquals("0", result);

        assertEquals(2, server.getMessageCounter());
        socket2.close();
    }


    @Override
    protected void setUp() throws Exception {
        server = new Server();
        server.launch(null);
    }

    @Override
    protected void tearDown() throws Exception {
        server.close();
    }
}
