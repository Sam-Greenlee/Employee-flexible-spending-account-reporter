//Programmer: Samuel Greenlee
//Program: Java03 Program Assignment
//Description: should receive information
//	about an employee’s request for funds from
//	their flexible spending accounts, and write
//	that information to a text file
//Date Created On: 3/31/2020

package db;

import java.util.List;

public interface DAO<T> {
    T get(int empID);
    List<T> getAll();
    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);
}