package java.web.controller.command;

/**
 * Created by Олег on 31.03.2016.
 */
public enum  CommandName {
    LOGIN_USER, // user->client, admin, dispatcher
    REGISTER_USER, // user->client
    REGISTER_DISPATCHER, // admin
    DELETE_USER, // admin, client(this)
    SHOW_USER_LIST, // admin
    ADD_NEW_LOAD, // dispatcher
    ADD_NEW_TRANSPORT, // dispatcher
    REMOVE_LOAD, // dispatcher
    REMOVE_TRANSPORT, // dispatcher
    CORRECT_LOAD, // dispatcher
    CORRECT_TRANSPORT, // dispatcher
    SHOW_LOAD_LIST, // all
    SHOW_TRANSPORT_LIST, // all
    SHOW_ORDER, // client(this)
    SHOW_ORDER_LIST, // admin
    REMOVE_ORDER, // admin, client(this)
}
