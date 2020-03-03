package com.github.howwrite.mars.sdk.extension;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * @author howwrite
 * @Description 扩展request，实现可以加入parameter
 * @create 2019/10/5 22:20
 */
public class MarsParameterServletRequest extends HttpServletRequestWrapper {
    private Map<String, String[]> parameterMap = new HashMap<String, String[]>();

    /**
     * Creates a ServletRequest adaptor wrapping the given request object.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public MarsParameterServletRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = super.getParameterMap();
        this.parameterMap.putAll(parameterMap);
        return this.parameterMap;
    }

    public void putParameter(String name, String[] value) {
        parameterMap.put(name, value);
    }

    public void putParameter(String name, String value) {
        putParameter(name, new String[]{value});
    }

    @Override
    public String getParameter(String name) {
        String parameter = super.getParameter(name);
        if (StringUtils.isEmpty(parameter)) {
            String[] strings = parameterMap.get(name);
            if (strings == null || strings.length == 0) {
                return "";
            }
            return strings[0];
        }
        return parameter;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        if (parameterValues == null || parameterValues.length == 0) {
            parameterValues = parameterMap.get(name);
        }
        return parameterValues;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Enumeration<String> parameterNames = super.getParameterNames();
        List<String> list = new Vector<>();
        list.addAll(Collections.list(parameterNames));
        list.addAll(parameterMap.keySet());
        return Collections.enumeration(list);
    }
}
