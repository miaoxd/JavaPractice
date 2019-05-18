package testGenericKeyedObjectPool;

/**
 * @author exinmia
 * @since 2014
 *
 */
public class KeyObject {
    private String host;

    private int port;

    private String loginUser;

    public KeyObject(final String host, final int port, final String loginUser) {
        this.host = host;
        this.port = port;
        this.loginUser = loginUser;
    }

    @Override
    public int hashCode() {
        int result = this.host.hashCode();
        result = 29 * result + this.loginUser.hashCode() + port;
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        KeyObject that = (KeyObject) o;

        if (!host.equals(that.getHost())) {
            return false;
        }
        if (!loginUser.equals(that.getLoginUser())) {
            return false;
        }
        if (port != that.getPort()) {
            return false;
        }

        return true;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

}
