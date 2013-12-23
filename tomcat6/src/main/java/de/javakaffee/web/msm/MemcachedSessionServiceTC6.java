/*
 * Copyright 2009 Martin Grotzke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.javakaffee.web.msm;

import org.apache.catalina.Context;
import org.apache.catalina.authenticator.Constants;
import org.apache.catalina.deploy.LoginConfig;
import org.apache.catalina.deploy.SecurityConstraint;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
public class MemcachedSessionServiceTC6 extends MemcachedSessionService {

    public MemcachedSessionServiceTC6(SessionManager manager) {
        super(manager);
    }

    private Boolean _contextHasFormBasedSecurityConstraint;

    protected boolean contextHasFormBasedSecurityConstraint(){
        if(_contextHasFormBasedSecurityConstraint != null) {
            return _contextHasFormBasedSecurityConstraint.booleanValue();
        }
        final Context context = (Context)_manager.getContainer();
        final SecurityConstraint[] constraints = context.findConstraints();
        final LoginConfig loginConfig = context.getLoginConfig();
        _contextHasFormBasedSecurityConstraint = constraints != null && constraints.length > 0
                && loginConfig != null && Constants.FORM_METHOD.equals( loginConfig.getAuthMethod() );
        return _contextHasFormBasedSecurityConstraint;
    }
}