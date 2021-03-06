package classes.web.controller;

import classes.web.controller.exception.ControllerException;
import classes.web.entity.Load;
import classes.web.entity.Transport;
import classes.web.entity.user.Admin;
import classes.web.entity.user.Client;
import classes.web.entity.user.Dispatcher;
import classes.web.model.service.ModificationService;
import classes.web.model.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

import static classes.web.entity.user.User.*;

public class ModificationController {
    private static final String HAVE_BEEN_CREATED_FORMAT = "The %s-%s have been created!";

    public static String AddNewUserController(HttpServletRequest request, UserType userType) throws ControllerException {
        String result = "";
        String adminLogin = (String) request.getSession().getAttribute("login");
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm");
        try {
            if (login == null || name == null || surname == null || password == null || confirmPassword == null ||
                login.equals("") || name.equals("") || surname.equals("") || password.equals("") || confirmPassword.equals(""))
                    throw new ControllerException ("Not all fields are field!");
            if (!confirmPassword.equals(password))
                throw new ControllerException("Inc");
            switch (userType) {
                case CLIENT: {
                    Client client = new Client();
                    client.setLogin(login);
                    client.setName(name);
                    client.setSurname(surname);
                    client.setPassword(password);
                    if (ModificationService.addNewClientService(client))
                        result = String.format(HAVE_BEEN_CREATED_FORMAT, "client", login);
                } break;
                case DISPATCHER: {
                    Dispatcher dispatcher = new Dispatcher();
                    dispatcher.setLogin(login);
                    dispatcher.setName(name);
                    dispatcher.setSurname(surname);
                    dispatcher.setPassword(password);
                    if (ModificationService.addNewDispatcherService(dispatcher, adminLogin))
                        result = String.format(HAVE_BEEN_CREATED_FORMAT, "dispatcher", login);
                } break;
                case ADMIN: {
                    Admin admin = new Admin();
                    admin.setLogin(login);
                    admin.setName(name);
                    admin.setSurname(surname);
                    admin.setPassword(password);
                    if (ModificationService.addNewAdminService(admin, adminLogin))
                        result = String.format(HAVE_BEEN_CREATED_FORMAT, "admin", login);
                } break;
                case VISITOR: throw new ControllerException("User type isn't chosen!");
            }
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }
        return result;
    }

    public static String removeUserController(HttpServletRequest request) throws ControllerException {
        String result = "";
        try {
            String login = request.getParameter("delete_user");
            String adminLogin = request.getSession().getAttribute("login").toString();
            if (!login.equals("")) {
                if (login.equals(adminLogin))
                    throw new ControllerException("Can't remove itself!");
                if (ModificationService.removeUserService(login, adminLogin))
                    result = String.format("User - %s deleted!", login);
            } else
                throw new ControllerException("Login field isn't filled!");
        } catch (ServiceException | ControllerException ex) {
            throw new ControllerException(ex.getMessage());
        }
        return result;
    }
    public static String removeLoadController(HttpServletRequest request) throws ControllerException{
        try {
            String login = request.getSession().getAttribute("login").toString();
            if (request.getParameter("delete_load").equals(""))
                throw new ControllerException("Not all fields are field");
            int loadId = Integer.parseInt(request.getParameter("delete_load"));
            UserType userType = (UserType) request.getSession().getAttribute("user_type");
            switch (userType) {
                case CLIENT: {
                    return ModificationService.removeClientLoadService(loadId, login);
                }
                case DISPATCHER: {
                    return ModificationService.removeFreeLoadService(loadId, login);
                }
            }
        } catch (ControllerException | ServiceException | RuntimeException ex) {
            throw new ControllerException(ex.getMessage());
        }
        return "";
    }
    public static String removeTransportController(HttpServletRequest request) throws ControllerException{
        try {
            String login = request.getSession().getAttribute("login").toString();
            int state_number = Integer.parseInt(request.getParameter("delete_transport"));
            UserType userType = (UserType) request.getSession().getAttribute("user_type");
            switch (userType) {
                case CLIENT: {
                    return ModificationService.removeClientTransportService(state_number, login);
                }
                case DISPATCHER: {
                    return ModificationService.removeFreeTransportService(state_number, login);
                }
            }
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        } catch (RuntimeException ex) {
            throw new ControllerException("Incorrect data, where number is needed!");
        }
        return "";
    }

    public static String addClientLoadController(HttpServletRequest request) throws ControllerException {
        try {
            String login = request.getSession().getAttribute("login").toString();
            int loadID = Integer.parseInt(request.getParameter("add_load"));
            return ModificationService.addClientLoadService(loadID, login);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        } catch (RuntimeException ex) {
            throw new ControllerException("Incorrect input!");
        }
    }
    public static String addFreeLoadController(HttpServletRequest request) throws ControllerException {
        try {
            if ((UserType) request.getSession().getAttribute("user_type") != UserType.DISPATCHER)
                throw  new ControllerException("You are not a dispatcher!");
            else {
                String dispatcherLogin = (String) request.getSession().getAttribute("login");
                String weight = request.getParameter("weight");
                String costOfDelivery = request.getParameter("cost_of_delivery");
                String loadType = request.getParameter("load_type");
                String description = request.getParameter("load_description");
                if (dispatcherLogin == null || weight == null || costOfDelivery == null || loadType == null || description == null ||
                        dispatcherLogin.equals("") || weight.equals("") || costOfDelivery.equals("") || loadType.equals("") || description.equals(""))
                    throw new ControllerException("Not all fields are filled!");
                else {
                    Load load = new Load();
                    load.setWeight(Integer.parseInt(weight));
                    load.setCostOfDelivery(Integer.parseInt(costOfDelivery));
                    switch (loadType) {
                        case "dangerous":
                            load.setLoadType(1);
                            break;
                        case "perishable":
                            load.setLoadType(2);
                            break;
                        case "superheavy":
                            load.setLoadType(3);
                            break;
                        case "alive":
                            load.setLoadType(4);
                            break;
                        case "bulky":
                            load.setLoadType(5);
                            break;
                        default:
                            throw new ControllerException("Incorrect data!");
                    }
                    load.setLoadDescription(description);
                    if (ModificationService.addFreeLoadService(load, dispatcherLogin))
                        return "Load added!";
                    else
                        return "";
                }
            }
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        } catch (RuntimeException ex) {
            throw new ControllerException("Incorrect input!");
        }
    }
    public static String addClientTransportController(HttpServletRequest request) throws ControllerException {
        try {
            String login = request.getSession().getAttribute("login").toString();
            int state_number = Integer.parseInt(request.getParameter("add_transport"));
            return ModificationService.addClientTransportService(state_number, login);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        } catch (RuntimeException ex) {
            throw new ControllerException("Incorrect input!");
        }
    }
    public static String addFreeTransportController(HttpServletRequest request) throws ControllerException {
        try {
            String dispatcherLogin = (String) request.getSession().getAttribute("login");
            String state_number = request.getParameter("state_number");
            String model = request.getParameter("model");
            String tonnage = request.getParameter("tonnage");
            String trailerType = request.getParameter("trailer_type");
            String paymentForKilometer = request.getParameter("payment_for_kilometer");
            if (dispatcherLogin == null || state_number == null || model == null || tonnage == null || trailerType == null || paymentForKilometer == null ||
                    dispatcherLogin.equals("") || state_number.equals("") || model.equals("") || tonnage.equals("") || trailerType.equals("") || paymentForKilometer.equals(""))
                throw new ControllerException("Not all fields are filled!");
            else {
                Transport transport = new Transport();
                transport.setStateNumber(Integer.parseInt(state_number));
                transport.setModel(model);
                transport.setTonnage(Integer.parseInt(tonnage));
                transport.setPaymentForKilometer(Integer.parseInt(paymentForKilometer));
                switch (trailerType) {
                    case "semi-trailer": transport.setTrailerType(1); break;
                    case "refrigerator": transport.setTrailerType(2); break;
                    case "open-platform": transport.setTrailerType(3); break;
                    case "road-train": transport.setTrailerType(4); break;
                    case "thermos": transport.setTrailerType(5); break;
                    case "jumbo": transport.setTrailerType(6); break;
                    default: throw new ControllerException("Incorrect data!");
                }
                if (ModificationService.addFreeTransportService(transport, dispatcherLogin))
                    return "Transport added!";
                else
                    return "";
            }
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        } catch (RuntimeException ex) {
            throw new ControllerException("Incorrect input!");
        }
    }

    public static String updateLoadController(HttpServletRequest request) throws ControllerException {
        try {
            if ((UserType) request.getSession().getAttribute("user_type") != UserType.DISPATCHER &&
                (UserType) request.getSession().getAttribute("user_type") != UserType.CLIENT)
                    throw  new ControllerException("You are not a dispatcher!");
            else {
                String dispatcherLogin = (String) request.getSession().getAttribute("login");
                String loadID = request.getParameter("load_id");
                String weight = request.getParameter("weight");
                String costOfDelivery = request.getParameter("cost_of_delivery");
                String loadType = request.getParameter("load_type");
                String description = request.getParameter("load_description");
                if (loadID == null || weight == null || costOfDelivery == null || loadType == null || description == null ||
                    loadID.equals("") || weight.equals("") || costOfDelivery.equals("") || loadType.equals("") || description.equals(""))
                    throw new ControllerException("Not all fields are filled!");
                else {
                    Load load = new Load();
                    load.setLoadID(Integer.parseInt(loadID));
                    load.setWeight(Integer.parseInt(weight));
                    load.setCostOfDelivery(Integer.parseInt(costOfDelivery));
                    switch (loadType) {
                        case "dangerous":
                            load.setLoadType(1);
                            break;
                        case "perishable":
                            load.setLoadType(2);
                            break;
                        case "superheavy":
                            load.setLoadType(3);
                            break;
                        case "alive":
                            load.setLoadType(4);
                            break;
                        case "bulky":
                            load.setLoadType(5);
                            break;
                        default:
                            throw new ControllerException("Incorrect data!");
                    }
                    load.setLoadDescription(description);
                    if (ModificationService.updateLoadService(load, dispatcherLogin))
                        return "Load" + loadID + "updated!";
                    else
                        return "";
                }
            }
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        } catch (RuntimeException ex) {
            throw new ControllerException("Incorrect input!");
        }
    }
    public static String updateTransportController(HttpServletRequest request) throws ControllerException {
        try {
            if ((UserType) request.getSession().getAttribute("user_type") != UserType.DISPATCHER &&
                    (UserType) request.getSession().getAttribute("user_type") != UserType.CLIENT)
                throw  new ControllerException("You are not a dispatcher!");
            else {
                String dispatcherLogin = (String) request.getSession().getAttribute("login");
                String state_number = request.getParameter("state_number");
                String model = request.getParameter("model");
                String tonnage = request.getParameter("tonnage");
                String trailerType = request.getParameter("trailer_type");
                String paymentForKilometer = request.getParameter("payment_for_kilometer");
                if (state_number == null || model == null || tonnage == null || trailerType == null || paymentForKilometer == null ||
                        state_number.equals("") || model.equals("") || tonnage.equals("") || trailerType.equals("") || paymentForKilometer.equals(""))
                    throw new ControllerException("Not all fields are filled!");
                else {
                    Transport transport = new Transport();
                    transport.setStateNumber(Integer.parseInt(state_number));
                    transport.setModel(model);
                    transport.setTonnage(Integer.parseInt(tonnage));
                    transport.setPaymentForKilometer(Integer.parseInt(paymentForKilometer));
                    switch (trailerType) {
                        case "semi-trailer": transport.setTrailerType(1); break;
                        case "refrigerator": transport.setTrailerType(2); break;
                        case "open-platform": transport.setTrailerType(3); break;
                        case "road-train": transport.setTrailerType(4); break;
                        case "thermos": transport.setTrailerType(5); break;
                        case "jumbo": transport.setTrailerType(6); break;
                        default: throw new ControllerException("Incorrect data!");
                    }
                    if (ModificationService.updateTransportService(transport, dispatcherLogin))
                        return "Transport added!";
                    else
                        return "";
                }
            }
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        } catch (RuntimeException ex) {
            throw new ControllerException("Incorrect input!");
        }
    }
}
