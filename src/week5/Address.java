package week5;

import java.util.StringJoiner;

public class Address {
    private final String name;
    private final String company;
    private final String address;
    private final String zip;
    private final String phone;
    private final String email;

    public Address(String[] params) {
        name = params[0];
        company = params[1];
        address = params[2];
        zip = params[3];
        phone = params[4];
        email = params[5];
    }

    @Override
    public String toString() {
        return name + '\n' +
                "\t" + "Company: " + company + '\n' +
                "\t" + "Address: " + address + '\n' +
                "\t" + "Zipcode: " + zip + '\n' +
                "\t" + "Phones: " + phone + '\n' +
                "\t" + "Email: " + email + '\n';
    }

    public String getName() {
        return name;
    }

    public String saveFormat() {
        StringJoiner sj = new StringJoiner(",");
        return sj.add(name)
                .add(company)
                .add(address)
                .add(zip)
                .add(phone)
                .add(email)
                .toString();
    }
}