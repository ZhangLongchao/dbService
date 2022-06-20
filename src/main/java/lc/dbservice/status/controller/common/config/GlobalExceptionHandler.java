package lc.dbservice.status.controller.common.config;

//import com.flowsea.rcs.admin.aop.CauseSiteAspect;
import com.flowsea.common.result.BusinessException;
import com.flowsea.common.result.JsonResult;
import com.flowsea.common.result.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.util.Assert;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.dao.DuplicateKeyException;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * 全局的异常处理，
 *
 * @author me  2020-08-20 17:55
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Autowired
    private BasicErrorController controller;

    @Autowired
    private DefaultErrorAttributes attributes;

    /**
     * 程序出现问题的异常
     *
     * @param e e
     * @return Json
     */
    @ExceptionHandler(Exception.class)
    public JsonResult<Void> handle(Exception e) {
        log.warn("unknown exception", e);
        return JsonResult.trace(ResultStatus.UNKNOWN_ERROR, e.getMessage(), buildDetail(e));
    }



    @ExceptionHandler(NoHandlerFoundException.class)
    public JsonResult<Void> handle(NoHandlerFoundException e) {
        final String msg = String.format("请求的路径[%s:%s]不存在", e.getHttpMethod(), e.getRequestURL());
        return JsonResult.trace(ResultStatus.NOT_FOUND, msg, buildDetail(e));
    }


    /**
     * 业务异常
     *
     * @param e e
     * @return Json
     */
    @ExceptionHandler(BusinessException.class)
    public JsonResult<Void> handle(BusinessException e) {
        log.info("business exception status={},message={}", e.getCode(), e.getMessage());
        return JsonResult.trace(e.getCode(), e.getMessage(), buildDetail(e));
    }


    /**
     * 参数非法异常
     *
     * @param e e
     * @return Json
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult<Void> handle(MethodArgumentNotValidException e) {
        final String list = e.getBindingResult().getFieldErrors()
                .stream()
                .map(i -> String.format("[%s]:%s", i.getField(), i.getDefaultMessage()))
                .collect(Collectors.joining(", "));
        return JsonResult.trace(ResultStatus.BAD_REQUEST, list, buildDetail(e));
    }

    /**
     * 唯一索引异常
     *
     * @param e e
     * @return Json
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public JsonResult<Void> handle(DuplicateKeyException e) {
        log.warn("table unique index error.", e);
        return JsonResult.trace(ResultStatus.DB_UNIQUE_INDEX_ERROR, e.getMessage(), buildDetail(e));
    }

    @ExceptionHandler(CannotCreateTransactionException.class)
    public JsonResult<Void> handle(CannotCreateTransactionException e) {
        log.warn("datasource connection error.", e);
        return JsonResult.trace(ResultStatus.DB_CONN_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), buildDetail(e));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public JsonResult<Void> handle(DataIntegrityViolationException e) {
        log.warn("a foreign key constraint fails", e);
        return JsonResult.trace(ResultStatus.DB_FOREIGN_KEY_ERROR, e.getMessage(), buildDetail(e));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult<Void> handle(HttpRequestMethodNotSupportedException e) {
        return JsonResult.trace(ResultStatus.BAD_REQUEST, "请求方式错误:cause:" + e.getMessage(), buildDetail(e));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonResult<Void> handle(HttpMessageNotReadableException e) {
        return JsonResult.trace(ResultStatus.BAD_REQUEST, "请求体错误：cause:" + e.getMessage(), buildDetail(e));
    }


    private Map<String, Object> buildDetail(Exception e) {
        final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        //noinspection ConstantConditions
        attributes.resolveException(attr.getRequest(), attr.getResponse(), null, e);
        final Map<String, Object> body = controller.error(attr.getRequest()).getBody();
        Assert.notNull(body, "body");
        Optional.ofNullable(body.get("trace")).ifPresent(t -> log.warn("", (Throwable) t));
        return body;
    }


}
