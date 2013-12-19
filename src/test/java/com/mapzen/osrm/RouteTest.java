package com.mapzen.osrm;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class RouteTest {
    String routeJson =  "{\n" +
            "    \"alternative_geometries\": [],\n" +
            "    \"alternative_indices\": [],\n" +
            "    \"alternative_instructions\": [],\n" +
            "    \"alternative_names\": [\n" +
            "        [\n" +
            "            \"\",\n" +
            "            \"\"\n" +
            "        ]\n" +
            "    ],\n" +
            "    \"alternative_summaries\": [],\n" +
            "    \"hint_data\": {\n" +
            "        \"checksum\": 0,\n" +
            "        \"locations\": [\n" +
            "            \"yhZmAQqkAAAdAAAA____f13_oOjJA8Y_5G5sAiwDl_s\",\n" +
            "            \"WEtmAdIPAADHAAAAjAAAAEceNbcnvuI_NaZsAkw1l_s\"\n" +
            "        ]\n" +
            "    },\n" +
            // sample geometry taken from:
            // https://developers.google.com/maps/documentation/utilities/polylinealgorithm?csw=1
            // Points: (38.5, -120.2), (40.7, -120.95), (43.252, -126.453)
            "    \"route_geometry\": \"_p~iF~ps|U_ulLnnqC_mqNvxq`@\",\n" +
            "    \"route_instructions\": [\n" +
            "        [\n" +
            "            \"10\",\n" +
            "            \"19th Street\",\n" +
            "            160,\n" +
            "            0,\n" +
            "            0,\n" +
            "            \"160m\",\n" +
            "            \"SE\",\n" +
            "            128\n" +
            "        ],\n" +
            "        [\n" +
            "            \"7\",\n" +
            "            \"7th Avenue\",\n" +
            "            1937,\n" +
            "            1,\n" +
            "            0,\n" +
            "            \"1937m\",\n" +
            "            \"NE\",\n" +
            "            38\n" +
            "        ],\n" +
            "        [\n" +
            "            \"7\",\n" +
            "            \"Union Street\",\n" +
            "            97,\n" +
            "            29,\n" +
            "            0,\n" +
            "            \"97m\",\n" +
            "            \"NW\",\n" +
            "            297\n" +
            "        ],\n" +
            "        [\n" +
            "            \"15\",\n" +
            "            \"\",\n" +
            "            0,\n" +
            "            30,\n" +
            "            0,\n" +
            "            \"\",\n" +
            "            \"N\",\n" +
            "            0.0\n" +
            "        ]\n" +
            "    ],\n" +
            "    \"route_name\": [\n" +
            "        \"19th Street\",\n" +
            "        \"7th Avenue\"\n" +
            "    ],\n" +
            "    \"route_summary\": {\n" +
            "        \"end_point\": \"Union Street\",\n" +
            "        \"start_point\": \"19th Street\",\n" +
            "        \"total_distance\": 2195,\n" +
            "        \"total_time\": 211\n" +
            "    },\n" +
            "    \"status\": 0,\n" +
            "    \"status_message\": \"Found route between points\",\n" +
            "    \"via_indices\": [\n" +
            "        0,\n" +
            "        30\n" +
            "    ],\n" +
            "    \"via_points\": [\n" +
            "        [\n" +
            "            40.660708,\n" +
            "            -73.989332\n" +
            "        ],\n" +
            "        [\n" +
            "            40.674869,\n" +
            "            -73.9765\n" +
            "        ]\n" +
            "    ]\n" +
            "}\n";

    private Route route;

    @Before
    public void setup() throws Exception {
        route = new Route(routeJson);
    }

    @Test
    public void isObject() throws Exception {
        assert(route != null);
    }

    @Test
    public void hasTotalDistance() throws Exception {
        assert(route.getTotalDistance() != 0);
    }

    @Test
    public void hasCorrectTotalDistance() throws Exception {
        assert(route.getTotalDistance() == 2195);
    }

    @Test
    public void hasTotalTime() throws Exception {
        assert(route.getTotalTime() != 0);
    }

    @Test
    public void hasCorrectTotalTime() throws Exception {
        assert(route.getTotalTime() == 211);
    }

    @Test
    public void hasRouteInstruction() throws Exception {
        assert(route.getRouteInstruction() instanceof Instruction);
    }

    @Test
    public void hasGeometry() throws Exception {
        assert(route.getGeometry() != null);
    }

    @Test
    public void hasDecodedGeometry() throws Exception {
        // sample geometry taken from:
        // https://developers.google.com/maps/documentation/utilities/polylinealgorithm?csw=1
        // Points: (38.5, -120.2), (40.7, -120.95), (43.252, -126.453)
        // persision is different so that's why the numbers are wonkey
        // "_p~iF~ps|U_ulLnnqC_mqNvxq`@\
        ArrayList<double[]> list = route.getGeometry();

        assert(list.get(0)[0] == 3.85);
        assert(list.get(0)[1] == -12.02);

        assert(list.get(1)[0] == 4.07);
        assert(list.get(1)[1] == -12.095);

        assert(list.get(2)[0] == 4.3252);
        assert(list.get(2)[1] == -12.6453);
    }
}