package de.forminput.resources;

import org.apache.wicket.request.resource.PackageResourceReference;

public class BootstrapJsResourceReference extends PackageResourceReference {

    private static final BootstrapJsResourceReference INSTANCE = new BootstrapJsResourceReference();

    public BootstrapJsResourceReference() {
        super(BootstrapJsResourceReference.class, "bootstrap.js");
    }

    public static BootstrapJsResourceReference get() {
        return INSTANCE;
    }
}
