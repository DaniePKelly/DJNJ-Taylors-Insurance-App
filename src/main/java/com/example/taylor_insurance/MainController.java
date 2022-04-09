package com.example.taylor_insurance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class MainController {


    QuoteManager quoteManager = new QuoteManager();
    PolicyManager policyManager = new PolicyManager();

    //TODO use this or not?
    public static final String CUSTOMER = "/customers";
    public static final String HOME = "/homes";
    public static final String AUTO = "/autos";
    public static final String AUTOQUOTE = "/autoquotes";
    public static final String HOMEQUOTE = "/homequotes";
    public static final String HOMEPOLICY = "/homepolicy";
    public static final String AUTOPOLICY = "/autopolicy";

    @Autowired  //wires the customerRepository
    private CustomerRepository customerRepository;
    @Autowired  //wires the homeRepository
    private HomeRepository homeRepository;
    @Autowired  //wires the autoRepository
    private AutoRepository autoRepository;
    @Autowired  //wires the autoQuoteRepository
    private  AutoQuoteRepository autoQuoteRepository;
    @Autowired  //wires the homeQuoteRepository
    private  HomeQuoteRepository homeQuoteRepository;
    @Autowired
    private  HomePolicyRepository homePolicyRepository;
    @Autowired
    private AutoPolicyRepository autoPolicyRepository;

    @GetMapping(CUSTOMER +"/byid")
    public @ResponseBody
    Optional<Customer> getCustomerWithId(@RequestParam Integer customerId){
        return customerRepository.findById(customerId);
    }

    @GetMapping(path = CUSTOMER)
    public @ResponseBody
    Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    //TODO add @ApiResponse paramenters

    //TODO Use buttons that go to path?
    @PostMapping(path = CUSTOMER +"/byid")
    public @ResponseBody
    String addNewCustomer(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam LocalDate dateOfBirth){
        //TODO Add Builder patter?
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setDateOfBirth(dateOfBirth);
        customerRepository.save(customer);
        return "Saved";
    }

    @GetMapping(CUSTOMER +"/byid"+ HOME)
    public @ResponseBody
    Iterable<Home> getHomesByCustomerWithId(@RequestParam int customerId){
        return customerRepository.findById(customerId).get().getHomes();
    }

    @GetMapping(path = CUSTOMER + HOME)
    public @ResponseBody
    Iterable<Home> getAllHomes(){
        return homeRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + HOME)
    public @ResponseBody
    String addNewHome(@RequestParam Integer customerId,
                      @RequestParam String description, @RequestParam int value, @RequestParam LocalDate dateBuilt){
        Home home = new Home();
        home.setDescription(description);
        home.setValue(value);
        home.setDateBuilt(dateBuilt);
        //TODO pass these in too
        home.setHeatingType(Home.HeatingType.OIL_HEATING);
        home.setLocation(Home.Location.RURAL);

        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isPresent()){
            home.setCustomer(customer.get());
            homeRepository.save(home);

            //TODO confirm you don't have to add the home to the customer
           customer.get().getHomes().add(home);

            return "Saved";
        } else {
            return "No customer found";
        }
    }

    @GetMapping(CUSTOMER +"/byId"+ AUTO)
    public @ResponseBody
    Iterable<Auto> getAutoByCustomerWithId(@RequestParam int customerId){
        return customerRepository.findById(customerId).get().getAutos();
    }

    @GetMapping(path = CUSTOMER + AUTO)
    public @ResponseBody
    Iterable<Auto> getAllAutos(){
        return autoRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + AUTO)
    public @ResponseBody
    String addNewAuto(@RequestParam String model, @RequestParam int autoYear, @RequestParam String make,
                      @RequestParam int numberOfAccidents,@RequestParam Integer customerId ){

        Auto auto = new Auto();
        auto.setModel(model);
        auto.setAutoYear(autoYear);
        auto.setMake(make);
        auto.setNumberOfAccidents(numberOfAccidents);


        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isPresent()){
            auto.setCustomer(customer.get());
            autoRepository.save(auto);

            //TODO confirm you don't have to add the home to the customer
            customer.get().getAutos().add(auto);

            return "Saved";
        } else {
            return "No customer found";
        }

    }

    @GetMapping(CUSTOMER +"/byId"+ AUTOQUOTE)
    public @ResponseBody
    Iterable<AutoQuote> getAutoQuoteByCustomerWithId(@PathVariable Integer customerId){
        return customerRepository.findById(customerId).get().getAutoQuotes();
    }

    @GetMapping(path = CUSTOMER + AUTOQUOTE)
    public @ResponseBody
    Iterable<AutoQuote> getAllAutoQuotes(){
        return autoQuoteRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + AUTOQUOTE)
    public @ResponseBody
    String addNewAutoQuote(@RequestParam int customerId, @RequestParam int autoId){
        AutoQuote autoQuote =new AutoQuote();
        Auto autoForQuote = new Auto();
        Optional<Customer> customer = customerRepository.findById(customerId);
        for (Auto auto:customer.get().getAutos()) {
            if(auto.getAutoId() == autoId){
                autoForQuote = auto;
//                autoForQuote.setAutoId(autoId);
//                autoForQuote.setModel(auto.getModel());
//                autoForQuote.setMake(auto.getMake());
//                autoForQuote.setAutoYear(auto.getAutoYear());
//                autoForQuote.setNumberOfAccidents(auto.getNumberOfAccidents());

            }
        }
        //Uses quoteManager.getNewAutoQuote to get create the auto quote using the customer
        autoQuote = quoteManager.getNewAutoQuote(autoForQuote,customer.get().getDateOfBirth());

        if(customer.isPresent()){
            autoQuote.setCustomer(customer.get());
            autoQuoteRepository.save(autoQuote);

            //TODO confirm you don't have to add the home to the customer
            customer.get().getAutoQuotes().add(autoQuote);

            return "Saved";
        } else {
            return "No customer found";
        }
    }

    @GetMapping(CUSTOMER +"/byid"+ HOMEQUOTE)
    public @ResponseBody
    Iterable<HomeQuote> getHomeQuoteByCustomerWithId(@RequestParam int customerId){
        return customerRepository.findById(customerId).get().getHomeQuotes();
    }

    @GetMapping(path = CUSTOMER + HOMEQUOTE)
    public @ResponseBody
    Iterable<HomeQuote> getAllHomeQuotes(){
        return homeQuoteRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + HOMEQUOTE)
    public @ResponseBody
    String addNewHomeQuote(@RequestParam int customerId , @RequestParam int homeId){

        //TODO Run to get premium and auto quote
        HomeQuote homeQuote = new HomeQuote();
        Home homeForQuote = new Home();



        Optional<Customer> customer = customerRepository.findById(customerId);

        for (Home home:customer.get().getHomes()) {
            if(home.getHomeId() == homeId){
               homeForQuote = home;
            }
        }

        homeQuote = quoteManager.getNewHomeQuote(homeForQuote);

        if(customer.isPresent()){
            homeQuote.setCustomer(customer.get());
            homeQuote.setHome(homeForQuote);
            homeQuoteRepository.save(homeQuote);

            //TODO confirm you don't have to add the home to the customer
            customer.get().getHomeQuotes().add(homeQuote);

            return "Saved";
        } else {
            return "No customer found";
        }

    }


    @GetMapping(CUSTOMER +"/byid"+ AUTOPOLICY)
    public @ResponseBody
    Iterable<AutoPolicy> getAutoPolicyByCustomerWithId(@RequestParam int customerId){
        return customerRepository.findById(customerId).get().getAutoPolicies();
    }

    @GetMapping(path = CUSTOMER + AUTOPOLICY)
    public @ResponseBody
    Iterable<AutoPolicy> getAllAutoPolicies(){
        return autoPolicyRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + AUTOPOLICY)
    public @ResponseBody
    String addNewAutoPolicy(@RequestParam int customerId , @RequestParam int autoQuoteID){

        //TODO Run to get premium and auto quote


        Optional<Customer> customer = customerRepository.findById(customerId);

//        for (Home home:customer.get().getHomes()) {
//            if(home.getHomeId() == homeId){
//                homeForQuote = home;
//            }
//        }
//
//        homeQuote = quoteManager.getNewHomeQuote(homeForQuote);

        if(customer.isPresent()){
//            homeQuote.setCustomer(customer.get());
//            homeQuote.setHome(homeForQuote);
//            homeQuoteRepository.save(homeQuote);

            //TODO confirm you don't have to add the home to the customer
            //customer.get().getHomeQuotes().add(homeQuote);

            return "Saved";
        } else {
            return "No customer found";
        }

    }


    @GetMapping(CUSTOMER +"/byid" + HOMEPOLICY)
    public @ResponseBody
    Iterable<HomePolicy> getHomePolicyByCustomerWithId(@PathVariable Integer id){
        return customerRepository.findById(id).get().getHomePolicies();
    }

    @GetMapping(path = CUSTOMER + HOMEPOLICY)
    public @ResponseBody
    Iterable<HomePolicy> getAllHomePolicies(){
        return homePolicyRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + HOMEPOLICY)
    public @ResponseBody
    String addNewHomePolicy(@RequestParam int customerId , @RequestParam int homeQuoteID){

        //TODO Run to get premium and auto quote


        Optional<Customer> customer = customerRepository.findById(customerId);

        HomePolicy homePolicy = new HomePolicy();
        HomeQuote homeQuoteForPolicy = new HomeQuote();

        for (HomeQuote homeQuote:customer.get().getHomeQuotes()) {
            if(homeQuote.getHomeQuoteId() == homeQuoteID){
                homeQuoteForPolicy = homeQuote;
            }
        }

        homePolicy = policyManager.getNewHomePolicy(homeQuoteForPolicy);

        if(customer.isPresent()){
           homePolicy.setCustomer(customer.get());
           homePolicyRepository.save(homePolicy);

            //TODO confirm you don't have to add the home to the customer
            customer.get().getHomePolicies().add(homePolicy);

            return "Saved";
        } else {
            return "No customer found";
        }

    }


}
