package com.example.taylor_insurance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1")   //versioning the API
public class MainController {


    QuoteManager quoteManager = new QuoteManager();
    PolicyManager policyManager = new PolicyManager();

    //TODO use this or not?
    public static final String VERSION_1 = "/v1";
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

    @GetMapping(CUSTOMER + "/{id}")
    public @ResponseBody
    Optional<Customer> getCustomerWithId(@PathVariable Integer id){
        return customerRepository.findById(id);
    }

    @GetMapping(path = CUSTOMER)
    public @ResponseBody
    Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    //TODO add @ApiResponse paramenters

    //TODO Use buttons that go to path?
    @PostMapping(path = CUSTOMER)
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

    @GetMapping(CUSTOMER + "/{id}" + HOME)
    public @ResponseBody
    Iterable<Home> getHomesByCustomerWithId(@PathVariable Integer id){
        return customerRepository.findById(id).get().getHomes();
    }

    @GetMapping(path = CUSTOMER + HOME)
    public @ResponseBody
    Iterable<Home> getAllHomes(){
        return homeRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + "/{id}" + HOME)
    public @ResponseBody
    String addNewHome(@PathVariable Integer id,
                      @RequestParam String description, @RequestParam int value, @RequestParam LocalDate dateBuilt){
        Home home = new Home();
        home.setDescription(description);
        home.setValue(value);
        home.setDateBuilt(dateBuilt);
        //TODO pass these in too
        home.setHeatingType(Home.HeatingType.OIL_HEATING);
        home.setLocation(Home.Location.RURAL);

        Optional<Customer> customer = customerRepository.findById(id);

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

    @GetMapping(CUSTOMER + "/{id}" + AUTO)
    public @ResponseBody
    Iterable<Auto> getAutoByCustomerWithId(@PathVariable Integer id){
        return customerRepository.findById(id).get().getAutos();
    }

    @GetMapping(path = CUSTOMER + AUTO)
    public @ResponseBody
    Iterable<Auto> getAllAutos(){
        return autoRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + "/{id}" + AUTO)
    public @ResponseBody
    String addNewAuto(@PathVariable Integer id,
                      @RequestParam String model, @RequestParam int autoYear, @RequestParam String make,
                      @RequestParam int numberOfAccidents ){

        Auto auto = new Auto();
        auto.setModel(model);
        auto.setAutoYear(autoYear);
        auto.setMake(make);
        auto.setNumberOfAccidents(numberOfAccidents);
        Optional<Customer> customer = customerRepository.findById(id);

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

    @GetMapping(CUSTOMER + "/{id}" + AUTOQUOTE)
    public @ResponseBody
    Iterable<AutoQuote> getAutoQuoteByCustomerWithId(@PathVariable Integer id){
        return customerRepository.findById(id).get().getAutoQuotes();
    }

    @GetMapping(path = CUSTOMER + AUTOQUOTE)
    public @ResponseBody
    Iterable<AutoQuote> getAllAutoQuotes(){
        return autoQuoteRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + "/{id}" + AUTOQUOTE)
    public @ResponseBody
    String addNewAutoQuote(@PathVariable Integer id, @RequestParam int autoId){
        AutoQuote autoQuote =new AutoQuote();
        Auto autoForQuote = new Auto();
        Optional<Customer> customer = customerRepository.findById(id);
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

    @GetMapping(CUSTOMER + "/{id}" + HOMEQUOTE)
    public @ResponseBody
    Iterable<HomeQuote> getHomeQuoteByCustomerWithId(@PathVariable Integer id){
        return customerRepository.findById(id).get().getHomeQuotes();
    }

    @GetMapping(path = CUSTOMER + HOMEQUOTE)
    public @ResponseBody
    Iterable<HomeQuote> getAllHomeQuotes(){
        return homeQuoteRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + "/{id}" + HOMEQUOTE)
    public @ResponseBody
    String addNewHomeQuote(@PathVariable Integer id , @RequestParam int homeId){

        //TODO Run to get premium and auto quote
        HomeQuote homeQuote = new HomeQuote();
        Home homeForQuote = new Home();



        Optional<Customer> customer = customerRepository.findById(id);

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


    @GetMapping(CUSTOMER + "/{id}" + AUTOPOLICY)
    public @ResponseBody
    Iterable<AutoPolicy> getAutoPolicyByCustomerWithId(@PathVariable Integer id){
        return customerRepository.findById(id).get().getAutoPolicies();
    }

    @GetMapping(path = CUSTOMER + AUTOPOLICY)
    public @ResponseBody
    Iterable<AutoPolicy> getAllAutoPolicies(){
        return autoPolicyRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + "/{id}" + AUTOPOLICY)
    public @ResponseBody
    String addNewAutoPolicy(@PathVariable Integer id , @RequestParam int autoQuoteID){

        //TODO Run to get premium and auto quote


        Optional<Customer> customer = customerRepository.findById(id);

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


    @GetMapping(CUSTOMER + "/{id}" + HOMEPOLICY)
    public @ResponseBody
    Iterable<HomePolicy> getHomePolicyByCustomerWithId(@PathVariable Integer id){
        return customerRepository.findById(id).get().getHomePolicies();
    }

    @GetMapping(path = CUSTOMER + HOMEPOLICY)
    public @ResponseBody
    Iterable<HomePolicy> getAllHomePolicies(){
        return homePolicyRepository.findAll();
    }

    @PostMapping(path = CUSTOMER + "/{id}" + HOMEPOLICY)
    public @ResponseBody
    String addNewHomePolicy(@PathVariable Integer id , @RequestParam int homeQuoteID){

        //TODO Run to get premium and auto quote


        Optional<Customer> customer = customerRepository.findById(id);

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
