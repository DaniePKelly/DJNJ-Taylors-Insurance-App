function configurevehicle(make,model) {
    var Ford = ['F150', 'Bronco', 'Focus'];
    var Honda = ['Civic', 'Fit', 'HRV'];
    var Nissan = ['Rogue', 'Versa', 'Qashqai'];
    var Dodge = ['Ram', 'Charger', 'Dart']

    switch (make.value) {
        case 'Ford':
            model.options.length = 0;
            for (i = 0; i < Ford.length; i++) {
                createOption(model, Ford[i], Ford[i]);
            }
            break;
        case 'Honda':
            model.options.length = 0; 
        for (i = 0; i < Honda.length; i++) {
            createOption(model, Honda[i], Honda[i]);
            }
            break;
        case 'Nissan':
            model.options.length = 0;
            for (i = 0; i < Nissan.length; i++) {
                createOption(model, Nissan[i], Nissan[i]);
            }
            break;
            case 'Dodge':
                model.options.length = 0;
                for (i = 0; i < Dodge.length; i++) {
                    createOption(model, Dodge[i], Dodge[i]);
                }
                break;
            default:
                model.options.length = 0;
            break;
    }

}

    function createOption(ddl, text, value) {
        var opt = document.createElement('option');
        opt.value = value;
        opt.text = text;
        ddl.options.add(opt);
    }