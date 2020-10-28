package TpProg2.ImplementOfUsers;

import java.util.Calendar;

public class Date {

    public int mes, dia, hora;

    public Date(int mes, int dia, int hora){
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
    }

    public static int howLongAgo (Date date){
        Date today = actualDate();
        return dateDiference(today,date);
    } //Devuelve las horas transcurridas desde una fecha hasta el dia de hoy.

    public static int dateDiference (Date date1, Date date2){
        int date1Hours = pastMonthDays(date1.mes)*24 + date1.dia*24 + date1.hora;
        int date2Hours = pastMonthDays(date2.mes)*24 + date2.dia*24 + date2.hora;
        return date1Hours - date2Hours;
    }

    public static Date actualDate(){
        Calendar fecha = Calendar.getInstance();
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH)+1;
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        return new Date(mes, dia, hora);
    } //Devuelve la fecha actual (mes, dia, hora).

    public static int pastMonthDays(int mes){// Hay que hacer que devuelva la cantidad de dias que hubieron en los meses anteriores a ese (solo para 2020 en este caso)

        int numeroDias=-1;

        switch(mes){

            case 12:
                numeroDias += 30;
            case 11:
                numeroDias += 31;
            case 10:
                numeroDias += 30;
            case 9:
                numeroDias += 31;
            case 8:
                numeroDias += 31;
            case 7:
                numeroDias += 30;
            case 6:
                numeroDias += 31;
            case 5:
                numeroDias += 30;
            case 4:
                numeroDias += 31;
            case 3:
                numeroDias += 29;
            case 2:
                numeroDias += 31;
            case 1:
                break;
        }
        return numeroDias;
    } //Devuelve todos los dias que pasaron desde principio de año hasta el comienzo del mes ingresado.

    public static int monthDays(int mes, int año) {
        switch (mes) {
            case 1:  // Enero
            case 3:  // Marzo
            case 5:  // Mayo
            case 7:  // Julio
            case 8:  // Agosto
            case 10:  // Octubre
            case 12: // Diciembre
                return 31;
            case 4:  // Abril
            case 6:  // Junio
            case 9:  // Septiembre
            case 11: // Noviembre
                return 30;
            case 2:  // Febrero
                if (((año % 100 == 0) && (año % 400 == 0)) ||
                        ((año % 100 != 0) && (año % 4 == 0)))
                    return 29;  // Año Bisiesto
                else
                    return 28;
            default:
                return -1;
        }
    }
}
