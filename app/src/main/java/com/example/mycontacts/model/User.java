package com.example.mycontacts.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.mycontacts.util.DateConverter;

import java.util.Date;
@Entity(tableName = "users")
public class User {
    public static final int GENDER_MALE = 0;
    public static final int GENDER_FEMALE = 1;

    @PrimaryKey(autoGenerate = true)
    public final int id;
    @ColumnInfo(name = "first_name")
    public final String firstName;
    @ColumnInfo(name = "last_name")
    public final String lastName;
    @ColumnInfo(name = "brith_date")
    @TypeConverters(DateConverter.class)
    public final Date brithDate;
    public final int gender;
    public final boolean singel;
    @ColumnInfo(name = "some_time")
    @TypeConverters(DateConverter.class)
    public final Date someTime;

    public User(int id,String firstName, String lastName,
                Date brithDate, int gender,
                boolean singel, Date someTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.brithDate = brithDate;
        this.gender = gender;
        this.singel = singel;
        this.someTime = someTime;
    }
}
