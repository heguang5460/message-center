package heguang5460.github.io.message.dao.ex;

/**
 * DAO 层异常
 *
 * @author he guang
 * @date 2021/8/31 9:51
 */
public class DaoException extends RuntimeException {

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }
}
