/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dicisas;

import java.io.IOException;

public interface CurrencyService {
    double convert(String fromCurrencyCode, String toCurrencyCode, double amount) throws IOException;
}
