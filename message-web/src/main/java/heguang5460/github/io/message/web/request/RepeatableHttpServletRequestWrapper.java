package heguang5460.github.io.message.web.request;

import heguang5460.github.io.message.common.constants.Constants;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.springframework.util.StreamUtils;

/**
 * 定义包装类，做增强
 * 请求体可重复读
 *
 * @author heguang
 */
public class RepeatableHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] requestBody = null;

    /**
     * 构建该请求实体的时候，将流数据存储一份到字段requestBody中
     *
     * @param request
     * @throws IOException
     */
    public RepeatableHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        requestBody = StreamUtils.copyToByteArray(request.getInputStream());
    }

    /**
     * 从缓存字段requestBody中读取流数据
     *
     * @return
     * @throws IOException
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * 将缓存字段byte[]转为流数据ServletInputStream
     *
     * @return
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }

    /**
     * 读取请求中的流数据，转码成字符串返回
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String convertRequestBodyToJsonStr(HttpServletRequest request) throws IOException {
        if (request instanceof RepeatableHttpServletRequestWrapper) {
            RepeatableHttpServletRequestWrapper realRequest = (RepeatableHttpServletRequestWrapper) request;
            int contentLength = realRequest.getContentLength();
            if (request.getContentLength() > 0) {
                byte[] buffer = new byte[contentLength];
                for (int i = 0; i < contentLength; ) {
                    int readlen = realRequest.getInputStream().read(buffer, i, contentLength - i);
                    if (readlen == -1) {
                        break;
                    }
                    i += readlen;
                }
                String charEncoding = realRequest.getCharacterEncoding();
                if (charEncoding == null) {
                    charEncoding = "UTF-8";
                }
                return new String(buffer, charEncoding);
            }
        }
        return Constants.EMPTY_STRING;
    }
}