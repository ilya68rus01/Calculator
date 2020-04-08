package com.example.calculator;


import java.math.BigDecimal;

public class Calculator {

    private Boolean CheckOperation(char op) {
        return op == '+' || op == '-';
    }

    private BigDecimal calculated(BigDecimal firstOperand, char operation, BigDecimal secondOperand) {
        switch (operation) {
            case '+':
                return new BigDecimal(String.valueOf(firstOperand.add(secondOperand)) );
            case '-':
                return firstOperand.add(secondOperand.multiply(BigDecimal.valueOf(-1)));
            default:
                return BigDecimal.valueOf(0);
        }
    }

    public String calc(String str) {
        BigDecimal A = new BigDecimal(0);
        BigDecimal B = new BigDecimal(0);
        char Op='0';
        if (CheckOperation(str.charAt(0)))
            return "Error expected number";
        int currPos,lastPos=0;
        String value=new String();
        for(currPos=0;currPos<str.length();currPos++)
        {
            if(Character.isDigit(str.charAt(currPos)))
            {
                value+=str.charAt(currPos);
            }
            else
            {
                if(B.equals(new BigDecimal(0)))
                {
                    B=new BigDecimal(value);
                    value=new String();
                }
                else
                {
                    A=B;
                    B=new BigDecimal(value);
                    value=new String();
                }
                if(!A.equals(new BigDecimal(0)) && !B.equals(new BigDecimal(0))&& Op!='0')
                {
                    A=calculated(A,Op,B);
                    B=new BigDecimal(0);
                    Op='0';
                }
                if(CheckOperation(str.charAt(currPos)) )
                {Op=str.charAt(currPos);}

            }
        }
        if(A.equals(new BigDecimal(0)))
        {
            A = B;
            B=new BigDecimal(value);
        }
        if(B.equals(new BigDecimal(0)))
            B=new BigDecimal(value);
        A=calculated(A,Op,B);
        return A.toString();
    }
}
