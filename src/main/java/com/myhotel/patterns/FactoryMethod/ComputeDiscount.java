package com.myhotel.patterns.FactoryMethod;

public class ComputeDiscount {
    public int getDiscount(HolidayPromotion holidayPromotion){
        int totalDiscount=0;
        if (holidayPromotion instanceof FallHolidayPromotion){
            totalDiscount = holidayPromotion.getDiscount() + (int)holidayPromotion.getPercent();
        }else if (holidayPromotion instanceof SpringHolidayPromotion){
            totalDiscount = Math.abs(holidayPromotion.getDiscount() + (int)holidayPromotion.getPercent());
        }else if (holidayPromotion instanceof SummerHolidayPromotion){
            totalDiscount = Math.max(holidayPromotion.getDiscount() , (int)holidayPromotion.getPercent());
        }else if (holidayPromotion instanceof WinterHolidayPromotion){
            totalDiscount = Math.min(holidayPromotion.getDiscount() , (int)holidayPromotion.getPercent());
        }
        return totalDiscount;
    }
}
