package controllers;

import models.Todo;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import javax.inject.Inject;

import static play.libs.Json.toJson;

@Security.Authenticated(Secured.class)
@Api(value = "/api/todos", description = "Operations with Todos") @Security.Authenticated(Secured.class) 
public class TodoController extends Controller {

    @Inject FormFactory formFactory;

@ApiOperation(value = "get All Todos",
     notes = "Returns List of all Todos",
     response = Todo.class, 
     httpMethod = "GET") 
    public Result getAllTodos() {
        return ok(toJson(Todo.findByUser(SecurityController.getUser())));
    }

@ApiOperation( 
     nickname = "createTodo", 
     value = "Create Todo", 
     notes = "Create Todo record", 
     httpMethod = "POST", 
     response = Todo.class
 ) 
@ApiImplicitParams( 
     { 
          @ApiImplicitParam( 
               name = "body", 
               dataType = "Todo", 
               required = true, 
               paramType = "body", 
               value = "Todo" 
          ) 
     } 
) 
@ApiResponses( 
          value = { 
                  @com.wordnik.swagger.annotations.ApiResponse(code = 400, message = "Json Processing Exception") 
          } 
) 
    public Result createTodo() {
        Form<Todo> form = formFactory.form(Todo.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }
        else {
            Todo todo = form.get();
            todo.user = SecurityController.getUser();
            todo.save();
            return ok(toJson(todo));
        }
    }
    
}
