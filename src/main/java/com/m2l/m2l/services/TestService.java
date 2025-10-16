package com.m2l.m2l.services;

import com.m2l.m2l.entities.Test;
import com.m2l.m2l.request.TestRequest;

public interface TestService {
	Test save(TestRequest request);
}
