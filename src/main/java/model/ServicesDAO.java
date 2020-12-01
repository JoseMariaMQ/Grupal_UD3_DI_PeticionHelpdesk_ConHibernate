package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class ServicesDAO {

    public void insertService(Services service) {
        try {
            SessionFactory myFactory = SessionManagement.getMyFactory();
            Session mySession = myFactory.openSession();
            //Escritura del servicio
            mySession.beginTransaction();
            mySession.save(service);
            mySession.getTransaction().commit();
            System.out.println("Servicio insertado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Services> showServices() {
        try {
            SessionFactory myFactory = SessionManagement.getMyFactory();
            Session mySession = myFactory.openSession();
            //Obtenemos todos los registros de la tabla
            mySession.beginTransaction();
            Query query = mySession.createQuery("FROM Services");
            ArrayList<Services> services = (ArrayList<Services>) query.list();
            mySession.getTransaction().commit();
            return services;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateService(Services service) {
        try {
            SessionFactory myFactory = SessionManagement.getMyFactory();
            Session mySession = myFactory.openSession();
            //Actualizamos servicio
            mySession.beginTransaction();
            mySession.update(service);
            mySession.getTransaction().commit();
            System.out.println("Servicio actualizado correctamente");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
