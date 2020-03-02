/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 *
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.github.howwrite.mars.sdk.utils.wx.aes;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * SHA1 class
 *
 * @author weixin
 * 计算公众平台的消息签名接口.
 */
public class Sha1 {

    /**
     * 用SHA1算法生成安全签名
     *
     * @param array 需要加密的token,timestamp,noce,encrypt
     * @return 安全签名
     * @throws AesException
     */
    public static String getSha1(String... array) throws AesException {
        try {
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.COMPUTE_SIGNATURE_ERROR);
        }
    }


    public static String gen(String... arr) {
        if (StringUtils.isAnyEmpty(arr)) {
            throw new IllegalArgumentException("Illegal request parameters, some parameters are empty : " + Arrays.toString(arr));
        } else {
            Arrays.sort(arr);
            StringBuilder sb = new StringBuilder();
            String[] var2 = arr;
            int var3 = arr.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                String a = var2[var4];
                sb.append(a);
            }

            return DigestUtils.sha1Hex(sb.toString());
        }
    }
}
