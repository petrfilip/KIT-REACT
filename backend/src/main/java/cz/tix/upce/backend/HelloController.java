package cz.tix.upce.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping()
  public HelloDto hello(@RequestParam(name="name") String name) {
    return new HelloDto(name);
  }

}
