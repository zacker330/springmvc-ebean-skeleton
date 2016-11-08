package hello.demo.controller;

import com.avaje.ebean.EbeanServer;
import hello.demo.domain.Todo;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    EbeanServer server;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(name = "title", required = true, defaultValue = "") String title) {

        Todo todo = new Todo("title-" + RandomUtils.nextInt(20), "description-" + RandomUtils.nextInt(20));
        server.save(todo);
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("todoList", server.find(Todo.class).findList());

        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Todo> search(@RequestParam(name = "q", required = false, defaultValue = "") String q,
                             @RequestParam(name = "p", required = false, defaultValue = "0") int p) {
        logger.debug("search : " + q);
        return server.find(Todo.class).where().like("title", "%" + q + "%").findPagedList(p, 20).getList();
    }

}
